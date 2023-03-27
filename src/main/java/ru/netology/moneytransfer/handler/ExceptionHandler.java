package ru.netology.moneytransfer.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransfer.exception.BadCredentials;
import ru.netology.moneytransfer.exception.ErrorDeleteFile;
import ru.netology.moneytransfer.exception.ErrorInputData;
import ru.netology.moneytransfer.exception.UnauthorizedError;
import ru.netology.moneytransfer.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentials.class)
    public ResponseEntity<ErrorResponse> bcHandler(BadCredentials e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<ErrorResponse> eidHandler(ErrorInputData e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedError.class)
    public ResponseEntity<ErrorResponse> uaeHandler(UnauthorizedError e) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorDeleteFile.class)
    public ResponseEntity<ErrorResponse> edfHandler(ErrorDeleteFile e) {
        return ResponseEntity
            .internalServerError()
            .body(new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
