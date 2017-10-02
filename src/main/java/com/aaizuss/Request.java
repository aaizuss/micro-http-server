package com.aaizuss;

import java.util.Hashtable;
import java.util.Objects;

public class Request {
    private Hashtable<String,String> headers;
    private String uri;
    private String httpVersion;
    private String body;
    private String method;
    private String params;

    public Request() {
        headers = new Hashtable<>();
    }

    public Request(String method, String uri) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = "HTTP/1.1";
        this.params = "";
        this.headers = new Hashtable<>();
    }

    public Request(String method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.params = "";
        this.headers = new Hashtable<>();
    }

    public Request(String method, String uri, String params, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.params = params;
        this.headers = new Hashtable<>();
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        if (body == null) {
            return "";
        }
        return body;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getParams() {
        return params;
    }

    public String getHeader(String fieldName) {
        return getHeaders().get(fieldName);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUri(String requestTarget) {
        this.uri = requestTarget;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void addHeader(String fieldName, String fieldValue) {
        headers.put(fieldName, fieldValue);
    }

    public Hashtable<String, String> getHeaders() {
        return headers;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (!(o instanceof Request)) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(method, request.getMethod()) &&
                Objects.equals(uri, request.getUri()) &&
                Objects.equals(httpVersion, request.getHttpVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, uri, httpVersion);
    }


}
