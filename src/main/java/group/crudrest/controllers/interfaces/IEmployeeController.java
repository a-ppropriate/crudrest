package group.crudrest.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import jakarta.validation.Valid;

@RestController
public interface IEmployeeController {

    @GetMapping("/employees")
    List<Employee> all();

    @PostMapping("/employees")
    Employee newEmployee(@Valid @RequestBody Employee newEmployee);

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id);

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id);

    @GetMapping("/employees/{id}/tasks/")
    List<Task> tasksList(@PathVariable Long id);
}
