package com.gabrielFadul.taskManager.adapter.controller;

import com.gabrielFadul.taskManager.adapter.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.adapter.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
