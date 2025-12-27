package com.gabrielFadul.taskManager.controllers;

import com.gabrielFadul.taskManager.models.TaskModel;
import com.gabrielFadul.taskManager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Puxar todas as tasks (Listar)
    @GetMapping("/list")
    public List<TaskModel> listTaks(){
        return taskRepository.findAll();
    }

    // Criar uma task
    @PostMapping
    public void createTask(@RequestBody TaskModel taskModel){
        taskRepository.save(taskModel);
    }

    // Deletar uma task
    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }
}
