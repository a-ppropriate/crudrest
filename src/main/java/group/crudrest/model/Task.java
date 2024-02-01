package group.crudrest.model;

import jakarta.persistence.Column;

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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Description can not be blank")
    private String description;

    /*
     * @NotBlank(message = "Employee id can not be blank")
     * 
     * @Column(name="employee_id")
     * private Long employeeId;
     */

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    protected Task() {
    };

    public Task(String title, String description, Long employee_id) {
        this.title = title;
        this.description = description;
        // this.employee_id = employee_id;
    };

    @Override
    public String toString() {
        return String.format(
                "Task[id=%d, name='%s', title='%s', description='%s']",
                id, title, description);
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

}