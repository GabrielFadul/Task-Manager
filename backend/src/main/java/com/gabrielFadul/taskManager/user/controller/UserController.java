package com.gabrielFadul.taskManager.user.controller;

import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody @Valid UserDtoRequest userDtoRequest, UriComponentsBuilder uriBuilder){
        UserDtoResponse userResponse = userService.create(userDtoRequest);
        URI location = uriBuilder.path("/{id}").buildAndExpand(userResponse.id()).toUri();
        return ResponseEntity.created(location).body(userResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(){
        userService.deleteAccount();
        return ResponseEntity.noContent().build();
    }
}
