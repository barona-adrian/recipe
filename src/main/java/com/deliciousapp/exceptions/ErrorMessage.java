package com.deliciousapp.exceptions;

@lombok.Setter
@lombok.Getter
@lombok.AllArgsConstructor
@lombok.ToString
public class ErrorMessage {
    private String exception_string;
    private String exception_message;
    private String exception_stacktrace;
}
