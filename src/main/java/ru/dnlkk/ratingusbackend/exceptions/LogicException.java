package ru.dnlkk.ratingusbackend.exceptions;

public class LogicException extends RuntimeException {
    public LogicException() {
    }

    public LogicException(String message) {
        super(message);
    }
}
