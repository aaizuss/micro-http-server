package com.aaizuss;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser parser;
    private BufferedReader reader = getReaderForSimpleRequest();
    private BufferedReader postReader = getReaderForPostRequest();
    private BufferedReader paramsReader = getReaderForParamRequest();

    private BufferedReader getReaderForSimpleRequest() {
        String requestLine = "GET /path/to/file/index.html HTTP/1.1\n";
        String headers = "Host: www.example.com\nAccept-Language: en-us\n";
        String request = requestLine + headers;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getBytes());
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private BufferedReader getReaderForPostRequest() {
        String postRequestLine = "Post / HTTP/1.1\nHost: foo.com\n";
        String postHeaders = "Content-Type: application/x-wwww-form-urlencoded\nContent-Length: 13\r\n\r\n";
        String postBody = "say=Hi&to=Mom";
        String postRequest = postRequestLine + postHeaders + postBody;
        ByteArrayInputStream postStream = new ByteArrayInputStream(postRequest.getBytes());
        return new BufferedReader(new InputStreamReader(postStream));
    }

    private BufferedReader getReaderForParamRequest() {
        String rawParams = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String requestWithRawParams = "GET " + rawParams + " HTTP/1.1\n" + "Host: localhost:8080\n" +
                "Accept-Language: en-us\n";
        ByteArrayInputStream requestWithParamsStream = new ByteArrayInputStream(requestWithRawParams.getBytes());
        return new BufferedReader(new InputStreamReader(requestWithParamsStream));
    }

    @Before
    public void setUp() throws Exception {
        parser = new RequestParser();
    }

    @Test
    public void testParsesMethod() throws IOException {
        Request request = parser.parseRequest(reader);
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void testParsesTarget() throws IOException {
        Request request = parser.parseRequest(reader);
        assertEquals("/path/to/file/index.html", request.getUri());
    }

    @Test
    public void testParsesVersion() throws IOException {
        Request request = parser.parseRequest(reader);
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    public void testParsesRequest() throws IOException {
        Request request = parser.parseRequest(reader);

        assertEquals("/path/to/file/index.html", request.getUri());
        assertEquals("www.example.com", request.getHeaders().get("Host"));
        assertEquals("en-us", request.getHeaders().get("Accept-Language"));
    }

    @Test
    public void testParsesRequestWithBody() throws IOException {
        Request request = parser.parseRequest(postReader);

        assertEquals("/", request.getUri());
        assertEquals("foo.com", request.getHeaders().get("Host"));
        assertEquals("application/x-wwww-form-urlencoded", request.getHeaders().get("Content-Type"));
        assertEquals("13", request.getHeaders().get("Content-Length"));
        assertEquals("say=Hi&to=Mom", request.getBody());
    }

    @Test
    public void testParsesRequestWithParams() throws IOException {
        Request request = parser.parseRequest(paramsReader);
        String decodedParams = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff";
        assertEquals(decodedParams, request.getParams());
    }

}
