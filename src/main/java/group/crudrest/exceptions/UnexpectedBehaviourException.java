package group.crudrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something went wrong")
public class UnexpectedBehaviourException extends RuntimeException {
    public UnexpectedBehaviourException(String message) {
        super(message);
    }
}
