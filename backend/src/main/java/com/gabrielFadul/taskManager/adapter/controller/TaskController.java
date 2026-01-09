package com.gabrielFadul.taskManager.adapter.controller;

import com.gabrielFadul.taskManager.adapter.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.adapter.dto.TaskResponse;
import com.gabrielFadul.taskManager.adapter.dto.TaskUpdateRequest;
import com.gabrielFadul.taskManager.core.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listByUserId(){
        List<TaskResponse> responses = taskService.listByUserId();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskCreateRequest request, UriComponentsBuilder uriComponentsBuilder){
        TaskResponse response = taskService.create(request);
        URI location = uriComponentsBuilder.path("/{id}").buildAndExpand(response.id()).toUri();
       return ResponseEntity.status(HttpStatus.CREATED).location(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllByUser() {
        taskService.deleteAllByUser();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updatePatch(@PathVariable Long id, @RequestBody @Valid TaskUpdateRequest taskUpdateRequest){
        TaskResponse response = taskService.updatePatch(id, taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updatePut(@PathVariable Long id, @RequestBody @Valid TaskUpdateRequest taskUpdateRequest){
        TaskResponse response = taskService.updatePut(id, taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
