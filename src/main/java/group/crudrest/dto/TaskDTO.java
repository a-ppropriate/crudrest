package group.crudrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Task", description = "Task entity")
public class TaskDTO {
    @Schema(description = "Id")
    private Long id;
    @NotBlank(message = "Title can not be blank")
    @Schema(description = "Title")
    private String title;
    @NotBlank(message = "Description can not be blank")
    @Schema(description = "description")
    private String description;
    @NotNull(message = "Employee id can not be null")
    @Schema(description = "Id of employee responsible for task")
    private Long employee_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, title='%s', description='%s', employee_id='%d']",
                id, title, description, employee_id);
    }

}
