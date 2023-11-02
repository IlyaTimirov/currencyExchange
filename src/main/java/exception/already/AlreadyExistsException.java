package exception.already;

import exception.AppException;
import exception.Error;

public class AlreadyExistsException extends AppException {
    public AlreadyExistsException(Error error) {
        super(error);
    }
}
