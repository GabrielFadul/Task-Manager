package com.gabrielFadul.taskManager.task.mapper;

import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.dto.TaskUpdateRequest;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.user.model.UserModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    public TaskModel toEntity(TaskCreateRequest taskCreateRequest, UserModel user){
        return new TaskModel(taskCreateRequest.title(), taskCreateRequest.description(), user);
    }

    public TaskResponse toDto(TaskModel taskModel){
        return new TaskResponse(
                taskModel.getId(),
                taskModel.getTitle(),
                taskModel.getDescription(),
                taskModel.getStatusTask(),
                taskModel.getCreatedAt(),
                taskModel.getUpdatedAt()
        );
    }

    public void updateEntityFromDto(TaskUpdateRequest dto, TaskModel taskModel){
        if(dto == null) return;

        if(dto.title() != null){
            taskModel.setTitle(dto.title());
        }

        if(dto.description() != null){
            taskModel.setDescription(dto.description());
        }

        if(dto.statusTask() != null){
            taskModel.setStatusTask(dto.statusTask());
        }
    }

    public void updateEntityPut(TaskUpdateRequest taskUpdateRequest, TaskModel taskModel){
        if(taskUpdateRequest == null) return;

        taskModel.setTitle(taskUpdateRequest.title());
        taskModel.setDescription(taskUpdateRequest.description());
        taskModel.setStatusTask(taskUpdateRequest.statusTask());
    }
}
