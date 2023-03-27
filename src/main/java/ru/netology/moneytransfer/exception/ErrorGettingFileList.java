package ru.netology.moneytransfer.exception;

public class ErrorGettingFileList extends RuntimeException {
    public ErrorGettingFileList(String msg) {
        super(msg);
    }
}
