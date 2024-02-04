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

import group.crudrest.services.TaskService;
import jakarta.validation.Valid;
import group.crudrest.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class TaskController {
  @Autowired
  TaskService taskService;

  @GetMapping("/tasks")
  List<Task> all() {
    return taskService.getTasks();
  }

  @PostMapping("/tasks")
  Task newTask(@Valid @RequestBody Task newTask) {
    return taskService.createTask(newTask);
  }

  @GetMapping("/tasks/{id}")
  Task one(@PathVariable Long id) {
    return taskService.getTask(id);
  }

  @PutMapping("/tasks/{id}")
  Task replaceTask(@Valid @RequestBody Task newTask, @PathVariable Long id) {

    return taskService.updateTask(newTask, id);
  }

  @DeleteMapping("/tasks/{id}")
  void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
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