package group.crudrest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.repository.TaskRepository;
import jakarta.validation.Valid;
import group.crudrest.model.Task;
import group.crudrest.exceptions.TaskNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class TaskController {

  private final TaskRepository repository;

  TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/tasks")
  List<Task> all() {
    return repository.findAll();
  }

  @PostMapping("/tasks")
  Task newTask(@Valid @RequestBody Task newTask) {
    return repository.save(newTask);
  }

  @GetMapping("/tasks/{id}")
  Task one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  @PutMapping("/tasks/{id}")
  Task replaceTask(@Valid @RequestBody Task newTask, @PathVariable Long id) {

    return repository.findById(id)
        .map(task -> {
          task.setTitle(newTask.getTitle());
          task.setDescription(newTask.getDescription());

          return repository.save(task);
        }).orElseThrow(() -> new TaskNotFoundException(id));
  }

  @DeleteMapping("/tasks/{id}")
  void deleteTask(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}