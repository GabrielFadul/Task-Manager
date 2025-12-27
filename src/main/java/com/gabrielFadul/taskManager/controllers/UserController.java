package com.gabrielFadul.taskManager.controllers;

import com.gabrielFadul.taskManager.models.UserModel;
import com.gabrielFadul.taskManager.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/cadastrar")
    public UserModel salvarUser(@RequestBody UserModel userModel){
        if(userRepository.findByEmail(userModel.getEmail()).isPresent()){
            throw new RuntimeException("Usuário já existe. Tente novamente!");
        }

        return userRepository.save(userModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

}
