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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Employees", description = "employee management")
public interface IEmployeeController {

    @GetMapping("/employees")
    @Operation(summary = "Full employees list", description = "lists employees")
    List<EmployeeDTO> all();

    @PostMapping("/employees")
    @Operation(summary = "Add employee")
    EmployeeDTO newEmployee(@Valid @RequestBody EmployeeDTO newEmployee);

    @GetMapping("/employees/{id}")
    @Operation(summary = "Get employee")
    EmployeeDTO one(@PathVariable Long id);

    @PutMapping("/employees/{id}")
    @Operation(summary = "Replace employee")
    EmployeeDTO replaceEmployee(@Valid @RequestBody EmployeeDTO newEmployee, @PathVariable Long id);

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Delete employee")
    void deleteEmployee(@PathVariable Long id);

    @GetMapping("/employees/{id}/tasks/")
    @Operation(summary = "List employee's tasks")
    List<TaskDTO> tasksList(@PathVariable Long id);

    @GetMapping("/employees/{id}/assists_in_tasks/")
    @Operation(summary = "List tasks the employee assists with")
    List<TaskDTO> assistTasksList(@PathVariable Long id);

    @PutMapping("/employees/{id}/assists_in_tasks/{task_id}")
    @Operation(summary = "Add employee asists in task relation")
    EmployeeDTO addAssistanceInTask(@PathVariable Long id, @PathVariable Long task_id);

    @DeleteMapping("/employees/{id}/assists_in_tasks/{task_id}")
    @Operation(summary = "Remove employee asists in task relation")
    void deleteAssistanceInTask(@PathVariable Long id, @PathVariable Long task_id);

}
