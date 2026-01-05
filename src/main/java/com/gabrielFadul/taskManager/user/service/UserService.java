package com.gabrielFadul.taskManager.user.service;

import com.gabrielFadul.taskManager.user.domain.EmailAlreadyExistsException;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.mapper.UserMapper;
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
        if(userRepository.existsByEmail(userDtoRequest.email())){
            throw new EmailAlreadyExistsException(userDtoRequest.email());
        }
        UserModel userModel = userMapper.toEntity(userDtoRequest);
        UserModel savedUser = userRepository.save(userModel);
        return userMapper.toResponse(savedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
