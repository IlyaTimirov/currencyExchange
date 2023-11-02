package exception.validation;

import exception.AppException;
import exception.Error;

public class ValidationException extends AppException {
    public ValidationException(Error error) {
        super(error);
    }
}
