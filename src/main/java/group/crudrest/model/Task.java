package group.crudrest.model;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

//TODO: better/proper validation
//FIXME: task creation

@Entity
@Hidden
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Description can not be blank")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    protected Task() {
    };

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    };

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, title='%s', description='%s', employee='%s']",
                id, title, description, employee);
    }

    public Long getId() {
        return id;
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
        return this.employee.getId();
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}