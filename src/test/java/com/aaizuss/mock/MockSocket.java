package com.aaizuss.mock;

import com.aaizuss.io.Writer;
import com.aaizuss.http.Response;
import com.aaizuss.io.Reader;
import com.aaizuss.io.socket.SocketService;

import java.net.Socket;

public class MockSocket extends Socket implements SocketService {
    private Reader reader;
    private Writer writer;
    private String rawRequest;

    public MockSocket(String rawRequest) {
        this.reader = new MockRequestReader(rawRequest);
        this.writer = new MockResponseWriter();
        this.rawRequest = rawRequest;
    }

    public MockSocket(String rawRequest, Response response) {
        this.reader = new MockRequestReader(rawRequest);
        this.writer = new MockResponseWriter(response);
        this.rawRequest = rawRequest;
    }

    public Reader getRequestReader() {
        return reader;
    }

    public Writer getResponseWriter() {
        return writer;
    }
}
