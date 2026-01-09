package com.gabrielFadul.taskManager.user.service;

import com.gabrielFadul.taskManager.task.service.TaskService;
import com.gabrielFadul.taskManager.user.domain.EmailAlreadyExistsException;
import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.mapper.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy TaskService taskService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskService = taskService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDtoResponse create(UserDtoRequest userDtoRequest){
        if(userRepository.existsByEmail(userDtoRequest.email())){
            throw new EmailAlreadyExistsException(userDtoRequest.email());
        }
        UserModel userModel = userMapper.toEntity(userDtoRequest);
        String senhaCriptografada = passwordEncoder.encode(userDtoRequest.password());
        userModel.setPassword(senhaCriptografada);
        UserModel savedUser = userRepository.save(userModel);

        return userMapper.toResponse(savedUser);
    }

    public void deleteAccount(){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskService.deleteByUser(user.getId());
        userRepository.deleteById(user.getId());
    }

    public UserModel findEntityById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
