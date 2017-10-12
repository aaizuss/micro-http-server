package com.aaizuss;

import java.io.*;
import java.net.Socket;

public class MockSocket extends Socket {
    private String rawRequest = "";

    public MockSocket() throws IOException {}

    public MockSocket(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    public void setRequest(String request) {
        this.rawRequest = request;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(rawRequest.getBytes());
    }

    public OutputStream getOutputStream() throws IOException {
        return new DataOutputStream(new ByteArrayOutputStream());
    }
}
