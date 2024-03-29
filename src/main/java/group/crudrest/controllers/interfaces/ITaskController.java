package group.crudrest.controllers.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group.crudrest.dto.EmployeeDTO;
import group.crudrest.dto.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Tasks", description = "task management")
public interface ITaskController {

    @GetMapping("/tasks")
    @Operation(summary = "Paged task list", description = "lists tasks")
    Page<TaskDTO> all(@RequestParam(name = "page_offset") Optional<Integer> page_offset,
            @RequestParam(name = "page_size") Optional<Integer> page_size);

    @PostMapping("/tasks")
    @Operation(summary = "Add task")
    TaskDTO newTask(@Valid @RequestBody TaskDTO newTask);

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Get task")
    TaskDTO one(@PathVariable Long id);

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Update task")
    TaskDTO replaceTask(@Valid @RequestBody TaskDTO newTask, @PathVariable Long id);

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Delete task")
    void deleteTask(@PathVariable Long id);

    @GetMapping("/tasks/{id}/employee_assistants/")
    @Operation(summary = "List employees that assist with the task")
    List<EmployeeDTO> assistantEmployeesList(@PathVariable Long id);
}
