package com.example.demo.api.endpoint;


import com.example.demo.api.payload.request.UserRequest;
import com.example.demo.api.payload.response.UserResponse;
import com.example.demo.application.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
@Tag(name = "User API")

public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }
}
