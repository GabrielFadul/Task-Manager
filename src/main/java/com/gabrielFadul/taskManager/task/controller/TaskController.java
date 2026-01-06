package com.gabrielFadul.taskManager.task.controller;

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

    // Puxar todas as tasks (Listar)
    @GetMapping("/list")
    public List<TaskModel> listTaks(){
        return taskService.listTask();
    }

    // Criar uma task
    @PostMapping
    public void createTask(@RequestBody TaskModel taskModel){
        taskService.create(taskModel);
    }

    // Deletar uma task
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.delete(id);
    }
}
