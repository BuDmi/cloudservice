package ru.netology.cloud.exception;

public class ErrorUploadFile extends RuntimeException {
    public ErrorUploadFile(String msg) {
        super(msg);
    }
}
