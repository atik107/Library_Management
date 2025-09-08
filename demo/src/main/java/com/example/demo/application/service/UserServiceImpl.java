package com.example.demo.application.service;


import com.example.demo.api.payload.request.UserRequest;
import com.example.demo.api.payload.response.UserResponse;
import com.example.demo.application.domain.UserEntity;
import com.example.demo.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> userResponses = new ArrayList<UserResponse>();
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();

        for (UserEntity user : users) {
            userResponses.add(UserResponse.fromEntity(user));
        }
        return userResponses;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .name(userRequest.getName())
                .role(userRequest.getRole())
                .build();
        UserEntity saved = userRepository.save(userEntity);
        return UserResponse.fromEntity(saved);
    }
}
