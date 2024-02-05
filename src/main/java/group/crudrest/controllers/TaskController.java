package group.crudrest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.services.TaskService;
import jakarta.validation.Valid;
import group.crudrest.controllers.interfaces.ITaskController;
import group.crudrest.dto.TaskDTO;
import group.crudrest.model.Task;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class TaskController implements ITaskController {
  @Autowired
  TaskService taskService;

  @Autowired
  ModelMapper modelMapper;

  private Task mapTask(TaskDTO c) {
    Objects.requireNonNull(c);
    return modelMapper.map(c, Task.class);
  }

  private TaskDTO mapTask(Task c) {
    Objects.requireNonNull(c);
    return modelMapper.map(c, TaskDTO.class);
  }

  private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .collect(Collectors.toList());
  }

  @Override
  public List<TaskDTO> all() {
    return mapList(taskService.getTasks(), TaskDTO.class);
  }

  @Override
  public TaskDTO newTask(@Valid @RequestBody TaskDTO newTask) {

    return mapTask(taskService.createTask(mapTask(newTask)));
  }

  @Override
  public TaskDTO one(@PathVariable Long id) {
    return mapTask(taskService.getTask(id));
  }

  @Override
  public TaskDTO replaceTask(@Valid @RequestBody TaskDTO newTask, @PathVariable Long id) {

    return mapTask(taskService.updateTask(mapTask(newTask), id));
  }

  @Override
  public void deleteTask(@PathVariable Long id) {
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