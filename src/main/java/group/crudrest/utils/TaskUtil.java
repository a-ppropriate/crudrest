package group.crudrest.utils;

import java.util.Objects;
import java.util.Optional;

import group.crudrest.exceptions.TaskNotFoundException;
import group.crudrest.model.Task;
import group.crudrest.repository.TaskRepository;

public final class TaskUtil {

    private static TaskRepository taskRepository;

    public static void setTaskRepository(TaskRepository taskRepository) {
        TaskUtil.taskRepository = taskRepository;
    }

    private TaskUtil() {
    }

    public static Optional<Task> findTaskById(Long id) {
        Objects.requireNonNull(id);
        return taskRepository.findById(id);
    }

    public static Task getTaskById(Long id) {
        Objects.requireNonNull(id);
        return TaskUtil.findTaskById(id).orElseThrow(
                () -> {
                    throw new TaskNotFoundException(id);
                });
    }
}
