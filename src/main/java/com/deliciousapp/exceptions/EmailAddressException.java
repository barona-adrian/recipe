package com.deliciousapp.exceptions;

public class EmailAddressException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EmailAddressException(){ super(); }
    public EmailAddressException(String message){ super(message); }
    public EmailAddressException(String message, Throwable cause){ super(message, cause); }
    public EmailAddressException(Throwable cause){ super(cause); }
}
