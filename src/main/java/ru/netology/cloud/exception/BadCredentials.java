package ru.netology.cloud.exception;

public class BadCredentials extends RuntimeException {
    public BadCredentials(String msg) {
        super(msg);
    }
}
