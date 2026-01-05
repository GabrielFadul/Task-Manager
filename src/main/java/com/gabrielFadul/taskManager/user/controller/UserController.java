package com.gabrielFadul.taskManager.user.controller;

import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDtoResponse> salvarUser(@RequestBody @Valid UserDtoRequest userDtoRequest){
        UserDtoResponse userResponse = userService.saveUser(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
