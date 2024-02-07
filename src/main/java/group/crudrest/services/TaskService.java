package group.crudrest.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import group.crudrest.exceptions.TaskNotFoundException;
import group.crudrest.model.Task;
import group.crudrest.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        Objects.requireNonNull(id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task newTask) {
        Objects.requireNonNull(newTask);
        return taskRepository.save(newTask);
    }

    public Task updateTask(Task newTask, @PathVariable Long id) {
        Objects.requireNonNull(id);
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setEmployee(newTask.getEmployee());

                    return taskRepository.save(task);
                }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteTask(Long id) {
        Objects.requireNonNull(id);
        taskRepository.deleteById(id);
    }

}
