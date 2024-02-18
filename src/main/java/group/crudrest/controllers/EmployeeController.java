package group.crudrest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.services.EmployeeService;
import group.crudrest.utils.MappingUtil;
import jakarta.validation.Valid;
import group.crudrest.controllers.interfaces.IEmployeeController;
import group.crudrest.dto.EmployeeDTO;
import group.crudrest.dto.TaskDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class EmployeeController implements IEmployeeController {
  @Autowired
  EmployeeService employeeService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public Page<EmployeeDTO> all(@RequestParam(name = "page_offset") Optional<Integer> page_offset,
      @RequestParam(name = "page_size") Optional<Integer> page_size) {
    return MappingUtil.mapPage(employeeService.getEmployees(page_offset, page_size), EmployeeDTO.class);
  }

  @Override
  public EmployeeDTO newEmployee(@Valid @RequestBody EmployeeDTO newEmployee) {
    return MappingUtil.mapEmployee(employeeService.createEmployee(MappingUtil.mapEmployee(newEmployee)));
  }

  @Override
  public EmployeeDTO one(@PathVariable Long id) {
    return MappingUtil.mapEmployee(employeeService.getEmployee(id));
  }

  @Override
  public EmployeeDTO replaceEmployee(@Valid @RequestBody EmployeeDTO newEmployee, @PathVariable Long id) {
    return MappingUtil.mapEmployee(employeeService.updateEmployee(MappingUtil.mapEmployee(newEmployee), id));
  }

  @Override
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }

  @Override
  public List<TaskDTO> tasksList(@PathVariable Long id) {
    return MappingUtil.mapList(employeeService.getTaskList(id), TaskDTO.class);
  }

  @Override
  public List<TaskDTO> assistTasksList(@PathVariable Long id) {
    return MappingUtil.mapList(employeeService.getAssistedTaskList(id), TaskDTO.class);
  }

  @Override
  public EmployeeDTO addAssistanceInTask(@PathVariable Long id, @PathVariable Long task_id) {
    return MappingUtil.mapEmployee(employeeService.addAssistanceInTask(id, task_id));
  }

  @Override
  public void deleteAssistanceInTask(@PathVariable Long id, @PathVariable Long task_id) {
    employeeService.deleteAssistanceInTask(id, task_id);
  };

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