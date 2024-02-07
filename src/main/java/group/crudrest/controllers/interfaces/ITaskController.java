package group.crudrest.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import group.crudrest.dto.TaskDTO;
import jakarta.validation.Valid;

public interface ITaskController {

    @GetMapping("/tasks")
    List<TaskDTO> all();

    @PostMapping("/tasks")
    TaskDTO newTask(@Valid @RequestBody TaskDTO newTask);

    @GetMapping("/tasks/{id}")
    TaskDTO one(@PathVariable Long id);

    @PutMapping("/tasks/{id}")
    TaskDTO replaceTask(@Valid @RequestBody TaskDTO newTask, @PathVariable Long id);

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id);
}
