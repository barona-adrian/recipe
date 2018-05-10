package com.deliciousapp.exceptions;

public class HBaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public HBaseException(){ super(); }
    public HBaseException(String message){ super(message); }
    public HBaseException(String message, Throwable cause){ super(message, cause); }
    public HBaseException(Throwable cause){ super(cause); }
}
