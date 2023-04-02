package ru.netology.cloud.exception;

public class ErrorDeleteFile extends RuntimeException {
    public ErrorDeleteFile(String msg) {
        super(msg);
    }
}
