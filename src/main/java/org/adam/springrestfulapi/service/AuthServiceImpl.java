package org.adam.springrestfulapi.service;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.LoginUserRequest;
import org.adam.springrestfulapi.model.TokenResponse;
import org.adam.springrestfulapi.repository.UserRepository;
import org.adam.springrestfulapi.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpired(next30Day());

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpired())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Wrong");
        }
    }

    @Override
    public String logout(User user) {
        user.setToken(null);
        user.setTokenExpired(null);
        userRepository.save(user);
        return "Success";
    }

    public Long next30Day() {
        return System.currentTimeMillis() * (100 * 16 *24 *30);
    }
}
