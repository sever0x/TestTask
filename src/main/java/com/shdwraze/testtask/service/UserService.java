package com.shdwraze.testtask.service;

import com.shdwraze.testtask.model.entity.UserEntity;
import com.shdwraze.testtask.model.request.UserRequest;

public interface UserService {

    UserEntity createUser(UserRequest userRequest);
}
