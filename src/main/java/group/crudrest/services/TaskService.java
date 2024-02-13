package group.crudrest.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import group.crudrest.exceptions.TaskNotFoundException;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Optional<Task> findTaskById(Long id) {
        Objects.requireNonNull(id);
        return taskRepository.findById(id);
    }

    public Task getTaskById(Long id) {
        return this.findTaskById(id).orElseThrow(
                () -> {
                    throw new TaskNotFoundException(id);
                });
    }

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

    public List<Employee> getAssistantEmployeeList(@PathVariable Long id) {

        Task task = this.getTaskById(id);
        return task.getAssistedTasksRelations().stream().map(rel -> rel.getEmployee()).toList();
    }

    public void deleteTask(Long id) {
        Objects.requireNonNull(id);
        taskRepository.deleteById(id);
    }

}
