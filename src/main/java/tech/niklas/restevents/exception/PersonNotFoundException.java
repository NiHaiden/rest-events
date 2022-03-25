package tech.niklas.restevents.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException() {
        super("Person was not found!");
    }
}
