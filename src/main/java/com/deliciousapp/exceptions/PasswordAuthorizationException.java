package com.deliciousapp.exceptions;

public class PasswordAuthorizationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PasswordAuthorizationException(){ super(); }
    public PasswordAuthorizationException(String message){ super(message); }
    public PasswordAuthorizationException(String message, Throwable cause){ super(message, cause); }
    public PasswordAuthorizationException(Throwable cause){ super(cause); }
}
