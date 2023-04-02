package ru.netology.cloud.exception;

public class UnauthorizedError extends RuntimeException {
    public UnauthorizedError(String msg) {
        super(msg);
    }
}
