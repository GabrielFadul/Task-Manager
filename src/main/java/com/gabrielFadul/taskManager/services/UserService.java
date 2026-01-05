package com.gabrielFadul.taskManager.services;

import com.gabrielFadul.taskManager.models.UserModel;
import com.gabrielFadul.taskManager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel saveUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
