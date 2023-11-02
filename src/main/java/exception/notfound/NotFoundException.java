package exception.notfound;

import exception.AppException;
import exception.Error;

public class NotFoundException extends AppException {
    public NotFoundException(Error error) {
        super(error);
    }

}
