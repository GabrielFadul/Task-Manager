package com.gabrielFadul.taskManager.task.service;

import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> listTask(){
         return taskRepository.findAll();
    }

    public void create(TaskModel taskModel){
        taskRepository.save(taskModel);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }


}
