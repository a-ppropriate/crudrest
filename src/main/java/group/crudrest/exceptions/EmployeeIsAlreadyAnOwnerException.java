package group.crudrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Employee is already the owner")
public class EmployeeIsAlreadyAnOwnerException extends RuntimeException {
    public EmployeeIsAlreadyAnOwnerException(Long id, Long task_id) {
        super("Employee" + id + " is already an owner of task " + task_id);
    }
}
