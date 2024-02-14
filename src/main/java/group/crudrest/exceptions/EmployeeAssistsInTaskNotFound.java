package group.crudrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Assistance not found")
public class EmployeeAssistsInTaskNotFound extends RuntimeException {
    public EmployeeAssistsInTaskNotFound(EmployeeAssistsInTaskKey key) {
        super("Employee assistance not found with task " + key);
    }
}
