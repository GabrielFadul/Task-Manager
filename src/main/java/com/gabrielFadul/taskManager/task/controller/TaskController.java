package com.gabrielFadul.taskManager.task.controller;

import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.dto.TaskUpdateRequest;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import com.gabrielFadul.taskManager.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponse>> listByUserId(@PathVariable Long userId){
        List<TaskResponse> responses = taskService.listByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskCreateRequest request, UriComponentsBuilder uriComponentsBuilder){
        URI location = uriComponentsBuilder.path("/{id}").buildAndExpand(request.id()).toUri();
        TaskResponse response = taskService.create(request);
       return ResponseEntity.status(HttpStatus.CREATED).location(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllByUser(@RequestParam Long id) {
        taskService.deleteAllByUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updatePatch(@PathVariable Long id, @RequestBody TaskUpdateRequest taskUpdateRequest){
        TaskResponse response = taskService.updatePatch(id, taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updatePut(@PathVariable Long id, @RequestBody TaskUpdateRequest taskUpdateRequest){
        TaskResponse response = taskService.updatePut(id, taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
