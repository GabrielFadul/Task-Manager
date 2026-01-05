package com.gabrielFadul.taskManager.user.mapper;

import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import org.springframework.stereotype.Component;

@Component
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
