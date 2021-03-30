package pl.espeo.sampleproject.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(long id) {
        super("Element not found with id: " + id);
    }
}
