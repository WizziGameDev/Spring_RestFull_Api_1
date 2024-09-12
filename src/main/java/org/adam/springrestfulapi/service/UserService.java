package org.adam.springrestfulapi.service;

import org.adam.springrestfulapi.entity.User;
import org.adam.springrestfulapi.model.RegisterUserRequest;
import org.adam.springrestfulapi.model.UpdateUserRequest;
import org.adam.springrestfulapi.model.UserResponse;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    public void register(RegisterUserRequest request);

    public UserResponse get(User user);

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request);
}
