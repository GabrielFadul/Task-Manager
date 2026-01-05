package com.gabrielFadul.taskManager.controllers;

import com.gabrielFadul.taskManager.models.UserModel;
import com.gabrielFadul.taskManager.repository.UserRepository;
import com.gabrielFadul.taskManager.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public UserModel salvarUser(@RequestBody UserModel userModel){
        return userService.saveUser(userModel);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
