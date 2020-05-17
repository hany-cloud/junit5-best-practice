package net.hka.example.springmvctest.todo;

/**
 * This exception is thrown when the requested todo item
 * isn't found from the database.
 */
@SuppressWarnings("serial")
public class TodoItemNotFoundException extends RuntimeException {

    public TodoItemNotFoundException(String message) {
        super(message);
    }
}
