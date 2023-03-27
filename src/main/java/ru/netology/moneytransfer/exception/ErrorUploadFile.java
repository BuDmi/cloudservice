package ru.netology.moneytransfer.exception;

public class ErrorUploadFile extends RuntimeException {
    public ErrorUploadFile(String msg) {
        super(msg);
    }
}
