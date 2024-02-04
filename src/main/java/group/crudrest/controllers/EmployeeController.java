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

import group.crudrest.repository.EmployeeRepository;
import group.crudrest.services.EmployeeService;
import jakarta.validation.Valid;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.exceptions.EmployeeNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@RestController
class EmployeeController {
  @Autowired
  EmployeeService employeeService;

  @Autowired
  ModelMapper modelMapper;

  // private final EmployeeRepository repository;

  // EmployeeController(EmployeeRepository repository) {
  // this.repository = repository;
  // }

  @GetMapping("/employees")
  List<Employee> all() {
    return employeeService.getEmployees();
  }

  @PostMapping("/employees")
  Employee newEmployee(@Valid @RequestBody Employee newEmployee) {
    return employeeService.createEmployee(newEmployee);
  }

  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    return employeeService.getEmployee(id);
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
    return employeeService.updateEmployee(newEmployee, id);
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
    // employeeRepository.deleteById(id);
  }

  // Tasks:
  @GetMapping("/employees/{id}/tasks/")
  List<Task> tasksList(@PathVariable Long id) {
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