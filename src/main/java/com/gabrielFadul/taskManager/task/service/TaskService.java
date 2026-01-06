package com.gabrielFadul.taskManager.task.service;

import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.mapper.TaskMapper;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> listTask(){
       List<TaskResponse> listResponse = new ArrayList<>();
       List<TaskModel> lista = taskRepository.findAll();
       for(TaskModel taskModel : lista){
           TaskResponse taskResponse = taskMapper.toDto(taskModel);
           listResponse.add(taskResponse);
       }

       return listResponse;
    }

    public List<TaskResponse> listTaskById(Long id) {
        List<TaskResponse> listResponse = new ArrayList<>();
        List<TaskModel> lista = taskRepository.findByUserId(id);
        for(TaskModel taskModel : lista){
            TaskResponse taskResponse = taskMapper.toDto(taskModel);
            listResponse.add(taskResponse);
        }
        return listResponse;
    }



    @Transactional
    public TaskResponse create(TaskCreateRequest request){
        UserModel userModel = userRepository.findById(request.userID()).orElseThrow(UserNotFoundException::new);
        TaskModel taskModel = taskMapper.toEntity(request, userModel); // O Request da task + a entidade correlacionada
        taskRepository.save(taskModel);

        return taskMapper.toDto(taskModel);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }
}
