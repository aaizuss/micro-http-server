package com.aaizuss.http;

import java.util.Hashtable;

public class Response {
    private String status;
    private byte[] body;
    private Hashtable<String, String> headers;

    public Response(String status, byte[] body) {
        this(status, new Hashtable<>(), body);
    }

    public Response(String status, Hashtable<String,String> headers, byte[] body) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }

    public Response(String status) {
        this(status, new Hashtable<>(), "".getBytes());
    }

    public Response() {
        this("", new Hashtable<>(), "".getBytes());
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body.getBytes();
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getStatus() {
        return this.status;
    }

    public byte[] getBody() {
        return this.body;
    }

    public Hashtable<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String fieldName) {
        return headers.get(fieldName);
    }
}
