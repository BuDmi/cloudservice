package ru.netology.moneytransfer.exception;

public class UnauthorizedError extends RuntimeException {
    public UnauthorizedError(String msg) {
        super(msg);
    }
}
