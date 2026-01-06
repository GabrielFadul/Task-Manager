package com.gabrielFadul.taskManager.task.controller;

import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import com.gabrielFadul.taskManager.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> listTaks(){
        return taskService.listTask();
    }

    @GetMapping("/{id}")
    public List<TaskResponse> listTaskById(@PathVariable Long id){
        return taskService.listTaskById(id);
    }

    @PostMapping("/createTask")
    public TaskResponse createTask(@RequestBody TaskCreateRequest request){
       return taskService.create(request);
    }

    // Deletar uma task
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.delete(id);
    }

    @DeleteMapping("/user/{userId}/delete")
    public void deleteAllTaskByUser(@PathVariable Long userId){
        taskService.deleteAllByUser(userId);
    }
}
