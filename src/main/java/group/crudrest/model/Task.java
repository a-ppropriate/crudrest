package group.crudrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import group.crudrest.exceptions.UnexpectedBehaviourException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
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

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EmployeeAssistsInTask> employeeAssistantRelations;

    protected Task() {
    };

    public Task(String title, String description, Employee employee) {
        this.title = title;
        this.description = description;
        this.employee = employee;
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
        Employee emp = this.getEmployee();
        if (emp == null) {
            throw new UnexpectedBehaviourException(
                    "A proper Task is not supposed to exist without employee: " + this.toString());
        }
        return emp.getId();
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<EmployeeAssistsInTask> getAssistedTasksRelations() {
        return this.employeeAssistantRelations;
    }
}