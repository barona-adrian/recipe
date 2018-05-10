package com.deliciousapp.exceptions;

import com.deliciousapp.hbase.connection.HBaseConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> unknownException(Exception e){
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()){
            stackTrace.append(s.toString());
        }
        return new ResponseEntity(new ErrorMessage(e.toString(), e.getMessage(), stackTrace.toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PasswordAuthorizationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> passwordAuthorizationException(PasswordAuthorizationException e){
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()){
            stackTrace.append(s.toString());
        }
        return new ResponseEntity(new ErrorMessage(e.toString(), e.getMessage(), stackTrace.toString()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {HBaseException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> hbaseException(HBaseException e){
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()){
            stackTrace.append(s.toString());
        }
        return new ResponseEntity(new ErrorMessage(e.toString(), e.getMessage(), stackTrace.toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {EmailAddressException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> emailAddressException(EmailAddressException e){
        StringBuilder stackTrace = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()){
            stackTrace.append(s.toString());
        }
        return new ResponseEntity(new ErrorMessage(e.toString(), e.getMessage(), stackTrace.toString()),
                HttpStatus.UNAUTHORIZED);
    }

}
