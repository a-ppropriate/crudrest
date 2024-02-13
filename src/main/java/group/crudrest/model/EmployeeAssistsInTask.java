package group.crudrest.model;

import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class EmployeeAssistsInTask {
    @EmbeddedId
    EmployeeAssistsInTaskKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", nullable = false)
    Task task;

    protected EmployeeAssistsInTask() {
    }

    public EmployeeAssistsInTask(Employee employee, Task task) {
        this.employee = employee;
        this.task = task;
        this.id = new EmployeeAssistsInTaskKey(employee.getId(), task.getId());
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
