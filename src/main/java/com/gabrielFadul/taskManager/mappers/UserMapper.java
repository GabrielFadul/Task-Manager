package com.gabrielFadul.taskManager.mappers;

import com.gabrielFadul.taskManager.dtos.UserDtoRequest;
import com.gabrielFadul.taskManager.dtos.UserDtoResponse;
import com.gabrielFadul.taskManager.models.UserModel;

public class UserMapper {


    public UserModel toEntity(UserDtoRequest dtoRequest){
        UserModel userModel = new UserModel();
        userModel.setName(dtoRequest.name());
        userModel.setPassword(dtoRequest.password());
        userModel.setEmail(dtoRequest.email());
        return userModel;
    }

    public UserDtoResponse toResponse(UserModel userModel){
        return new UserDtoResponse(userModel.getName(), userModel.getEmail());
    }

}
