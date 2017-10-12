package com.aaizuss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public class MockBufferedReader {
    private BufferedReader reader;

    public MockBufferedReader(String rawRequest) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes());
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public MockBufferedReader(String requestLine, String headers) {
        String request = requestLine + headers;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getBytes());
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }
}
