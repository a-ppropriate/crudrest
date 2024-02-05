package group.crudrest.controllers.interfaces;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import group.crudrest.model.Task;
import jakarta.validation.Valid;

public interface ITaskController {
    @PostMapping("/tasks")
    Task newTask(@Valid @RequestBody Task newTask);

    @GetMapping("/tasks/{id}")
    Task one(@PathVariable Long id);

    @PutMapping("/tasks/{id}")
    Task replaceTask(@Valid @RequestBody Task newTask, @PathVariable Long id);

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id);
}
