package com.example.demo.application.service;

import com.example.demo.api.payload.request.UserRequest;
import com.example.demo.api.payload.response.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse  createUser(@RequestBody UserRequest userRequest);
}
