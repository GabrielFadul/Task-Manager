package com.gabrielFadul.taskManager.task;

import com.gabrielFadul.taskManager.task.domain.TaskNotFoundException;
import com.gabrielFadul.taskManager.task.dto.TaskCreateRequest;
import com.gabrielFadul.taskManager.task.dto.TaskResponse;
import com.gabrielFadul.taskManager.task.dto.TaskUpdateRequest;
import com.gabrielFadul.taskManager.task.enums.StatusTask;
import com.gabrielFadul.taskManager.task.mapper.TaskMapper;
import com.gabrielFadul.taskManager.task.model.TaskModel;
import com.gabrielFadul.taskManager.task.repository.TaskRepository;
import com.gabrielFadul.taskManager.task.service.TaskService;
import com.gabrielFadul.taskManager.user.model.UserModel;
import com.gabrielFadul.taskManager.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;


    @Test
    @DisplayName("Deve listar tarefas por ID de usuário com sucesso")
    void listByUserId_Success() {
        Long userId = 1L;
        TaskModel task = new TaskModel();
        TaskResponse response = new TaskResponse("Título", "Desc", StatusTask.EM_ANDAMENTO,
                LocalDateTime.now(), LocalDateTime.now(), userId);

        when(taskRepository.findByUserId(userId)).thenReturn(List.of(task));
        when(taskMapper.toDto(task)).thenReturn(response);

        List<TaskResponse> result = taskService.listByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(StatusTask.EM_ANDAMENTO, result.get(0).statusTask());
        verify(userService).findEntityById(userId); // Valida o Service-to-Service
    }

    @Test
    @DisplayName("Deve criar uma tarefa com status inicial")
    void create_Success() {
        TaskCreateRequest request = new TaskCreateRequest(null, "Nova Tarefa", "Desc", 1L);
        UserModel user = new UserModel();
        TaskModel task = new TaskModel();
        TaskResponse response = new TaskResponse("Nova Tarefa", "Desc", StatusTask.EM_ANDAMENTO,
                LocalDateTime.now(), null, 1L);

        when(userService.findEntityById(request.userID())).thenReturn(user);
        when(taskMapper.toEntity(request, user)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(response);

        TaskResponse result = taskService.create(request);

        assertNotNull(result);
        verify(taskRepository).save(task);
        assertEquals("Nova Tarefa", result.title());
    }

    @Test
    @DisplayName("Deve atualizar status da tarefa via Patch")
    void updatePatch_StatusChange() {
        Long taskId = 1L;
        TaskUpdateRequest request = new TaskUpdateRequest(null, null, StatusTask.FINALIZADO);
        TaskModel task = new TaskModel();
        TaskResponse response = new TaskResponse("Titulo Original", "Desc", StatusTask.FINALIZADO,
                LocalDateTime.now(), LocalDateTime.now(), 1L);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDto(task)).thenReturn(response);

        TaskResponse result = taskService.updatePatch(taskId, request);

        verify(taskMapper).updateEntityFromDto(request, task);
        assertEquals(StatusTask.FINALIZADO, result.statusTask());
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar agir sobre tarefa inexistente")
    void taskNotFound_Error() {
        Long taskId = 99L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.delete(taskId));
    }
}
