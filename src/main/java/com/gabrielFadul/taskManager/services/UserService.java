package com.gabrielFadul.taskManager.services;

import com.gabrielFadul.taskManager.dtos.UserDtoRequest;
import com.gabrielFadul.taskManager.dtos.UserDtoResponse;
import com.gabrielFadul.taskManager.mappers.UserMapper;
import com.gabrielFadul.taskManager.models.UserModel;
import com.gabrielFadul.taskManager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDtoResponse saveUser(UserDtoRequest userDtoRequest){
        UserModel userModel = userMapper.toEntity(userDtoRequest);
        UserModel savedUser = userRepository.save(userModel);
         return userMapper.toResponse(savedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
