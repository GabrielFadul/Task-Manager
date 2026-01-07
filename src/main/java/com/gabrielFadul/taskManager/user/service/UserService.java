package com.gabrielFadul.taskManager.user.service;

import com.gabrielFadul.taskManager.user.domain.EmailAlreadyExistsException;
import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
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

    public UserDtoResponse create(UserDtoRequest userDtoRequest){
        if(userRepository.existsByEmail(userDtoRequest.email())){
            throw new EmailAlreadyExistsException(userDtoRequest.email());
        }
        UserModel userModel = userMapper.toEntity(userDtoRequest);
        UserModel savedUser = userRepository.save(userModel);
        return userMapper.toResponse(savedUser);
    }

    public void delete(Long id){
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    public UserModel findEntityById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
