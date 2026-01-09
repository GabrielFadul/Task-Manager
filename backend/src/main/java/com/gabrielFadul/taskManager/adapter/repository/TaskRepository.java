package com.gabrielFadul.taskManager.adapter.repository;

import com.gabrielFadul.taskManager.core.model.TaskModel;
import com.gabrielFadul.taskManager.core.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(UserModel userModel);
    Optional<TaskModel> findByIdAndUserId(Long id, Long userId);
    void deleteByUserId(Long userId);
}
