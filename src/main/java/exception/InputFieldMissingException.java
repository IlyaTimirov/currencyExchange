package exception;

public class InputFieldMissingException extends AppException{
    public InputFieldMissingException() {
        super(Error.INPUT_FIELD_MISSING);
    }
}
