package org.adam.springrestfulapi.service;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.LoginUserRequest;
import org.adam.springrestfulapi.model.TokenResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    public TokenResponse login(LoginUserRequest request);

    public String logout(User user);
}
