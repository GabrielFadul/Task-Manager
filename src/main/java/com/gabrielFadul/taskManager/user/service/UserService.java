package com.gabrielFadul.taskManager.user.service;

import com.gabrielFadul.taskManager.user.domain.EmailAlreadyExistsException;
import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDtoResponse create(UserDtoRequest userDtoRequest){
        // 1. Validação de negócio
        if(userRepository.existsByEmail(userDtoRequest.email())){
            throw new EmailAlreadyExistsException(userDtoRequest.email());
        }

        // 2. Transforma DTO em Entity (a senha aqui ainda é a "bruta")
        UserModel userModel = userMapper.toEntity(userDtoRequest);

        // 3. Criptografia (Camada de Segurança)
        String senhaCriptografada = passwordEncoder.encode(userDtoRequest.password());
        userModel.setPassword(senhaCriptografada); // Atualiza a Entity

        // 4. Persistência
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
