package group.crudrest.model.composite_keys;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeAssistsInTaskKey implements Serializable {
    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "task_id")
    Long taskId;

    protected EmployeeAssistsInTaskKey() {

    }

    public EmployeeAssistsInTaskKey(Long employee_id, Long task_id) {
        Objects.requireNonNull(employee_id);
        Objects.requireNonNull(task_id);
        this.employeeId = employee_id;
        this.taskId = task_id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        EmployeeAssistsInTaskKey other = (EmployeeAssistsInTaskKey) obj;

        return (other.getEmployeeId() == this.getEmployeeId() && other.getTaskId() == this.getTaskId());
    }
}
