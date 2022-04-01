package tech.niklas.restevents.exception;

public class ConflictingEventException extends RuntimeException{
    public ConflictingEventException() {
        super("Conflicting Event!");
    }
}
