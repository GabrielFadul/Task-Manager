package com.gabrielFadul.taskManager.task.service;

import com.gabrielFadul.taskManager.task.domain.TaskNotFoundException;
import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.dto.TaskUpdateRequest;
import com.gabrielFadul.taskManager.task.mapper.TaskMapper;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import com.gabrielFadul.taskManager.user.domain.UserNotFoundException;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.reposity.UserRepository;
import com.gabrielFadul.taskManager.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserService userService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> listByUserId(Long id) {
        // Fail-Fast -> Verifica se o usuário existe antes de buscar as tarefas
        userService.findEntityById(id);

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
        UserModel userModel = userService.findEntityById(request.userID());
        TaskModel taskModel = taskMapper.toEntity(request, userModel);
        taskRepository.save(taskModel);

        return taskMapper.toDto(taskModel);
    }

    @Transactional
    public void delete(Long id){
       TaskModel task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
       taskRepository.delete(task);
    }

    @Transactional
    public void deleteAllByUser(Long id){
        taskRepository.deleteByUserId(id);
    }

    @Transactional
    public TaskResponse updatePatch(Long id, TaskUpdateRequest taskUpdateRequest){
        TaskModel taskModel = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        taskMapper.updateEntityFromDto(taskUpdateRequest, taskModel);
        return taskMapper.toDto(taskRepository.save(taskModel));
    }

    @Transactional
    public TaskResponse updatePut(Long id, TaskUpdateRequest taskUpdateRequest){
        TaskModel taskModel = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        taskMapper.updateEntityPut(taskUpdateRequest, taskModel);
        return taskMapper.toDto(taskRepository.save(taskModel));
    }
}
