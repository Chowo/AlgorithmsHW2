package org.example.exceptions;

public class NoSuchIntegerException extends RuntimeException {
    public NoSuchIntegerException(Integer item) {
        super (String.format("There is no String - [%s] in this list", item));
    }
}
