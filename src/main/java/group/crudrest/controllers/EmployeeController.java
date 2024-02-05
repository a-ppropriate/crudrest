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

import group.crudrest.services.EmployeeService;
import jakarta.validation.Valid;
import group.crudrest.controllers.interfaces.IEmployeeController;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class EmployeeController implements IEmployeeController {
  @Autowired
  EmployeeService employeeService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public List<Employee> all() {
    return employeeService.getEmployees();
  }

  @Override
  public Employee newEmployee(@Valid @RequestBody Employee newEmployee) {
    return employeeService.createEmployee(newEmployee);
  }

  @Override
  public Employee one(@PathVariable Long id) {
    return employeeService.getEmployee(id);
  }

  @Override
  public Employee replaceEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
    return employeeService.updateEmployee(newEmployee, id);
  }

  @Override
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }

  @Override
  public List<Task> tasksList(@PathVariable Long id) {
    return employeeService.getTaskList(id);
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