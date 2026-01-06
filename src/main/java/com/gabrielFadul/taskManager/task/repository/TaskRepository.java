package com.gabrielFadul.taskManager.task.repository;

import com.gabrielFadul.taskManager.task.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUserId(Long userId);
    Optional<TaskModel> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}
