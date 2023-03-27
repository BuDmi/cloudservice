package ru.netology.moneytransfer.exception;

public class BadCredentials extends RuntimeException {
    public BadCredentials(String msg) {
        super(msg);
    }
}
