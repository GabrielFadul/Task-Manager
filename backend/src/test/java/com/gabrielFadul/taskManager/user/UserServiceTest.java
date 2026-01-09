package com.gabrielFadul.taskManager.user;


import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
import com.gabrielFadul.taskManager.user.dto.UserDtoRequest;
import com.gabrielFadul.taskManager.user.dto.UserDtoResponse;
import com.gabrielFadul.taskManager.user.mapper.UserMapper;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import com.gabrielFadul.taskManager.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void returnUserWhenExists(){
        Long idExist = 2L;
        UserModel userMock = new UserModel();
        userMock.setId(idExist);
        userMock.setName("João da Silva");
        when(userRepository.findById(idExist)).thenReturn(Optional.of(userMock));

        UserModel resultado = userService.findEntityById(idExist);

        assertNotNull(resultado);
        assertEquals("João da Silva", resultado.getName());
        assertEquals(2L, resultado.getId());
        verify(userRepository, times(1)).findById(idExist);
    }

    @Test
    void returnExceptionWhenUserNotExist(){
        Long idNotExist = 99999L;
        when(userRepository.findById(idNotExist)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.findEntityById(idNotExist);
        });
    }

    @Test
    void returnConvertEntityIfDtoIsCorrect(){
        UserDtoRequest userDtoRequest = new UserDtoRequest("Caio", "12345678", "fadul@gmail");
        UserModel userModel = new UserModel();
        userModel.setName("Caio");
        userModel.setEmail("fadul@gmail");
        when(userMapper.toEntity(userDtoRequest)).thenReturn(userModel);

        UserModel userConvert = userMapper.toEntity(userDtoRequest);

        assertEquals("Caio", userConvert.getName());
        assertEquals("fadul@gmail", userConvert.getEmail());
        verify(userMapper, times(1)).toEntity(userDtoRequest);
    }

    @Test
    void returnDtoCorrect(){
        UserDtoRequest request = new UserDtoRequest("Cleber", "12345678", "cleber@email");
        UserModel requestUserModel = new UserModel();
        requestUserModel.setName("Cleber");
        UserModel returnDBModel = new UserModel();
        returnDBModel.setId(1L);
        returnDBModel.setName("Cleber");
        UserDtoResponse response = new UserDtoResponse(1L, "Cleber", "cleber@email");
        when(userMapper.toEntity(request)).thenReturn(requestUserModel);
        when(userRepository.save(any(UserModel.class))).thenReturn(returnDBModel);
        when(userMapper.toResponse(returnDBModel)).thenReturn(response);

        UserDtoResponse expectedResponse = userService.create(request);

        assertNotNull(expectedResponse.id());
        assertEquals(1L, expectedResponse.id());
        assertEquals("Cleber", expectedResponse.name());
        verify(userRepository, times(1)).save(requestUserModel);
    }

    @Test
    void deleteSucess(){
        Long id = 2L;
        UserModel expectedModel = new UserModel();
        expectedModel.setId(2L);
        when(userRepository.findById(id)).thenReturn(Optional.of(expectedModel));

        userService.delete(id);

        verify(userRepository, times(1)).deleteById(id);
        verify(userRepository).findById(id);
    }

    @Test
    void deleteThrowExceptionWhenIdDoesNotExist(){
        Long id = 9999L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.delete(id));
        verify(userRepository, never()).deleteById(anyLong());
    }
}
