package com.gabrielFadul.taskManager.task.mapper;

import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    // É preciso atrelar um model na relação, setar o ID manualmente é errado.Você avisa o hibernate que quem esta atrelado é uma entidade
    public TaskModel toEntity(TaskCreateRequest taskCreateRequest, UserModel user){ // Associa a Task  p/ um user
        return new TaskModel(taskCreateRequest.title(), taskCreateRequest.description(), user);
    }

    public TaskResponse toDto(TaskModel taskModel){
        return new TaskResponse(
                taskModel.getTitle(),
                taskModel.getDescription(),
                taskModel.getStatusTask(),
                taskModel.getCreatedAt(),
                taskModel.getUpdatedAt(),
                taskModel.getUser().getId() // Pego a entidade User já relacionada, usando o ID dela.
        );
    }


}
