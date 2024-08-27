package org.example.exceptions;

public class NoSuchStringException extends RuntimeException {
    public NoSuchStringException(String item) {
        super (String.format("There is no String - [%s] in this list", item));
    }
}
