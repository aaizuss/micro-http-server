package com.aaizuss.http;

public class Status {
    public static final String OK = "HTTP/1.1 200 OK";
    public static final String BAD_REQUEST = "HTTP/1.1 400 Bad Request";
    public static final String UNAUTHORIZED = "HTTP/1.1 401 Unauthorized";
    public static final String NOT_FOUND = "HTTP/1.1 404 Not Found";
    public static final String CREATED = "HTTP/1.1 201 Created";
    public static final String NO_CONTENT = "HTTP/1.1 204 No Content";
    public static final String PARTIAL = "HTTP/1.1 206 Partial Content";
    public static final String FOUND = "HTTP/1.1 302 Found";
    public static final String UNSUPPORTED_MEDIA_TYPE = "HTTP/1.1 415 Unsupported Media Type";
    public static final String PRECONDITION_FAILED = "HTTP/1.1 412 Precondition Failed";
    public static final String COFFEE_POT = "HTTP/1.1 418";
    public static final String NOT_IMPLEMENTED = "HTTP/1.1 501 Not Implemented";
    public static final String METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method Not Allowed";
}
