package com.gabrielFadul.taskManager.controllers;

import com.gabrielFadul.taskManager.dtos.UserDtoRequest;
import com.gabrielFadul.taskManager.dtos.UserDtoResponse;
import com.gabrielFadul.taskManager.models.UserModel;
import com.gabrielFadul.taskManager.repository.UserRepository;
import com.gabrielFadul.taskManager.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDtoResponse> salvarUser(@RequestBody UserDtoRequest userDtoRequest){
        UserDtoResponse userResponse = userService.saveUser(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
