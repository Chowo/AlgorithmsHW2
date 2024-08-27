package org.example.exceptions;

public class IndexIsOutOfBoundsException extends IndexOutOfBoundsException {
    public IndexIsOutOfBoundsException(int index) {
        super(String.format("Index [%s] is out of bounds of list", index));
    }
}
