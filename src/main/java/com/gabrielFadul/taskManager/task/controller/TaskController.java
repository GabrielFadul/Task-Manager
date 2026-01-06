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

    @GetMapping("/user/{userId}")
    public List<TaskResponse> listByUserId(@PathVariable Long id){
        return taskService.listByUserId(id);
    }

    @PostMapping
    public TaskResponse create(@RequestBody TaskCreateRequest request){
       return taskService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }

    @DeleteMapping
    public void deleteAllByUser(@RequestParam Long userId){ // Pega o ID da query parametro (DELETE /tasks?userId={userId})
        taskService.deleteAllByUser(userId);
    }
}
