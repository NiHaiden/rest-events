package tech.niklas.restevents.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Event was not found!");
    }
}
