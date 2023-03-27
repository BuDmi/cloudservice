package ru.netology.moneytransfer.exception;

public class ErrorDeleteFile extends RuntimeException {
    public ErrorDeleteFile(String msg) {
        super(msg);
    }
}
