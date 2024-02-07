package group.crudrest.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.dto.EmployeeDTO;
import group.crudrest.dto.TaskDTO;
import jakarta.validation.Valid;

@RestController
public interface IEmployeeController {

    @GetMapping("/employees")
    List<EmployeeDTO> all();

    @PostMapping("/employees")
    EmployeeDTO newEmployee(@Valid @RequestBody EmployeeDTO newEmployee);

    @GetMapping("/employees/{id}")
    EmployeeDTO one(@PathVariable Long id);

    @PutMapping("/employees/{id}")
    EmployeeDTO replaceEmployee(@Valid @RequestBody EmployeeDTO newEmployee, @PathVariable Long id);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id);

    @GetMapping("/employees/{id}/tasks/")
    List<TaskDTO> tasksList(@PathVariable Long id);
}
