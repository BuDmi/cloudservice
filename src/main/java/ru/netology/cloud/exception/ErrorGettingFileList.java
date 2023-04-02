package ru.netology.cloud.exception;

public class ErrorGettingFileList extends RuntimeException {
    public ErrorGettingFileList(String msg) {
        super(msg);
    }
}
