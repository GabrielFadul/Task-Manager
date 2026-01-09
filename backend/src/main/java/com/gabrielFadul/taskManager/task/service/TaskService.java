package com.gabrielFadul.taskManager.task.service;

import com.gabrielFadul.taskManager.task.domain.PermissionDeniedException;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, @Lazy UserService userService, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponse> listByUserId() {
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.findEntityById(user.getId());

        List<TaskResponse> listResponse = new ArrayList<>();
        List<TaskModel> lista = taskRepository.findByUser(user);
        for(TaskModel taskModel : lista){
            TaskResponse taskResponse = taskMapper.toDto(taskModel);
            listResponse.add(taskResponse);
        }
        return listResponse;
    }

    @Transactional
    public TaskResponse create(TaskCreateRequest request){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskModel taskModel = taskMapper.toEntity(request, user);
        taskRepository.save(taskModel);

        return taskMapper.toDto(taskModel);
    }

    @Transactional
    public void delete(Long id){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskModel task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        if(!task.getUser().getId().equals(user.getId())){
            throw new PermissionDeniedException();
        }
        taskRepository.delete(task);
    }

    @Transactional
    public void deleteAllByUser(){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskRepository.deleteByUserId(user.getId());
    }

    @Transactional
    public TaskResponse updatePatch(Long id, TaskUpdateRequest taskUpdateRequest){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskModel task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        if(!task.getUser().getId().equals(user.getId())){
            throw new PermissionDeniedException();
        }

        taskMapper.updateEntityFromDto(taskUpdateRequest, task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse updatePut(Long id, TaskUpdateRequest taskUpdateRequest){
        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TaskModel task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        if(!task.getUser().getId().equals(user.getId())){
            throw new PermissionDeniedException();
        }

        taskMapper.updateEntityPut(taskUpdateRequest, task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public void deleteByUser(Long id){
        taskRepository.deleteByUserId(id);
    }
}
