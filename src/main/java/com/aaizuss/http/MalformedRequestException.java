package com.aaizuss.http;

public class MalformedRequestException extends Exception {

    public MalformedRequestException() {
        super("Malformed request!");
    }
}
