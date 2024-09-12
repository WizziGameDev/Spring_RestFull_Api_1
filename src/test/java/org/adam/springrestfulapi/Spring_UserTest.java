package org.adam.springrestfulapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.RegisterUserRequest;
import org.adam.springrestfulapi.model.UserResponse;
import org.adam.springrestfulapi.model.WebResponse;
import org.adam.springrestfulapi.repository.UserRepository;
import org.adam.springrestfulapi.security.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class Spring_UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testAPIUser() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Adam");
        request.setUsername("adamjordan");
        request.setPassword("rahasia");

        mockMvc.perform(
               post("/api/users")
                       .accept(MediaType.APPLICATION_JSON)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            Assertions.assertEquals("OK", response.getData());
        });
    }

    @Test
    public void testAPIUserErrors() throws Exception {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("");
        request.setUsername("");
        request.setPassword("");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
        });
    }

    @Test
    public void testGetUserUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "nothave")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    public void testGetUserUnauthorizedToken() throws Exception {
        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    public void testGetUserSuccess() throws Exception {
        User user = new User();
        user.setName("testP");
        user.setUsername("test");
        user.setPassword("test");
        user.setToken("PINPO");
        user.setTokenExpired(System.currentTimeMillis() + 100000000);
        userRepository.save(user);

        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "PINPO")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            Assertions.assertEquals("test", response.getData().getUsername());
        });
    }

    @Test
    public void testUpdateUserUnauthorizedToken () throws Exception {
        mockMvc.perform(
                get("/api/users/current")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});
            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    public void testLogout() throws Exception {
        User user = new User();
        user.setName("test");
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("hashpw", BCrypt.gensalt()));
        user.setToken("test");
        user.setTokenExpired(System.currentTimeMillis() + 100000000);

        userRepository.save(user);

        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

            Assertions.assertEquals("Success", response.getData());

            User id = userRepository.findById("test").orElse(null);
            Assertions.assertEquals(null, id.getToken());
        });

    }
}