package group.crudrest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.services.TaskService;
import group.crudrest.utils.MappingUtil;
import jakarta.validation.Valid;
import group.crudrest.controllers.interfaces.ITaskController;
import group.crudrest.dto.EmployeeDTO;
import group.crudrest.dto.TaskDTO;

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

  @Override
  public List<TaskDTO> all() {
    return MappingUtil.mapList(taskService.getTasks(), TaskDTO.class);
  }

  @Override
  public TaskDTO newTask(@Valid @RequestBody TaskDTO newTask) {

    return MappingUtil.mapTask(taskService.createTask(MappingUtil.mapTask(newTask)));
  }

  @Override
  public TaskDTO one(@PathVariable Long id) {
    return MappingUtil.mapTask(taskService.getTask(id));
  }

  @Override
  public TaskDTO replaceTask(@Valid @RequestBody TaskDTO newTask, @PathVariable Long id) {

    return MappingUtil.mapTask(taskService.updateTask(MappingUtil.mapTask(newTask), id));
  }

  @Override
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }

  @Override
  public List<EmployeeDTO> assistantEmployeesList(@PathVariable Long id) {
    return MappingUtil.mapList(taskService.getAssistantEmployeeList(id), EmployeeDTO.class);
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