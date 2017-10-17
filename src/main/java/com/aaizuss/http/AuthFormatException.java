package com.aaizuss.http;

public class AuthFormatException extends Exception {
    public AuthFormatException() {
        super("Invalid Authorization header format");
    }
}
