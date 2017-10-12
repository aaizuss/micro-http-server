package com.aaizuss.mock;

import com.aaizuss.Writer;
import com.aaizuss.http.Response;
import com.aaizuss.http.Status;
import com.aaizuss.socket.SocketService;

import java.net.Socket;

public class MockSocket extends Socket implements SocketService {
    private com.aaizuss.Reader reader;
    private com.aaizuss.Writer writer;
    private Response sampleResponse = new Response(Status.OK);
    private String rawRequest;
    private boolean isClosed;
    private String writerContent;

    public MockSocket(String rawRequest) {
        this.reader = new MockRequestReader(rawRequest);
        this.writer = new MockResponseWriter();
        this.rawRequest = rawRequest;
        this.isClosed = false;
    }

    public MockSocket(String rawRequest, Response response) {
        this.reader = new MockRequestReader(rawRequest);
        this.writer = new MockResponseWriter(response);
        this.rawRequest = rawRequest;
        this.isClosed = false;
    }

    public com.aaizuss.Reader getRequestReader() {
        return reader;
    }

    public Writer getResponseWriter() {
        return writer;
    }

//    public void close() throws IOException {
//        this.isClosed = true;
//    }
//
//    public OutputStream getOutputStream() throws IOException {
//        return new DataOutputStream(new ByteArrayOutputStream());
//    }
//
//    public InputStream getInputStream() throws IOException {
//        return new ByteArrayInputStream(rawRequest.getBytes());
//    }
//
//    public boolean isClosed() {
//        return isClosed;
//    }
}
