package com.aaizuss.http;

import com.aaizuss.mock.MockRequestReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser parser;
    private MockRequestReader reader = getReaderForSimpleRequest();
    private MockRequestReader postReader = getReaderForPostRequest();
    private MockRequestReader paramsReader = getReaderForParamRequest();
    private MockRequestReader malformedRequest = getReaderForMalformedRequest();

    private MockRequestReader getReaderForSimpleRequest() {
        String requestLine = "GET /path/to/file/index.html HTTP/1.1\n";
        String headers = "Host: www.example.com\nAccept-Language: en-us\n";
        String request = requestLine + headers;
        return new MockRequestReader(request);
    }

    private MockRequestReader getReaderForMalformedRequest() {
        String request = "GET/path/to/file HTTP/1.1\n";
        return new MockRequestReader(request);
    }

    private MockRequestReader getReaderForPostRequest() {
        String postRequestLine = "Post / HTTP/1.1\nHost: foo.com\n";
        String postHeaders = "Content-Type: application/x-wwww-form-urlencoded\nContent-Length: 13\r\n\r\n";
        String postBody = "say=Hi&to=Mom";
        String postRequest = postRequestLine + postHeaders + postBody;
        return new MockRequestReader(postRequest);
    }

    private MockRequestReader getReaderForParamRequest() {
        String rawParams = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String requestWithRawParams = "GET " + rawParams + " HTTP/1.1\n" + "Host: localhost:8080\n" +
                "Accept-Language: en-us\n";
        return new MockRequestReader(requestWithRawParams);
    }

    private MockRequestReader getReaderForMalformedHeaderRequest() {
        String requestLine = "GET /path/to/file HTTP/1.1\n";
        String badHeaders = "Content-Type:image/gif\r\n\r\n";
        String request = requestLine + badHeaders;
        return new MockRequestReader(request);
    }

    @Before
    public void setUp() throws Exception {
        parser = new RequestParser();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThrowsMalformedRequestForBadRequest() throws IOException, MalformedRequestException {
        thrown.expect(MalformedRequestException.class);
        Request request = parser.parseRequest(malformedRequest);
    }

    @Test
    public void testMalformedHeaders() throws IOException, MalformedRequestException {
        thrown.expect(MalformedRequestException.class);
        Request request = parser.parseRequest(getReaderForMalformedHeaderRequest());
    }

    @Test
    public void testParsesMethod() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(reader);
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void testParsesTarget() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(reader);
        assertEquals("/path/to/file/index.html", request.getUri());
    }

    @Test
    public void testParsesVersion() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(reader);
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    public void testParsesRequest() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(reader);

        assertEquals("/path/to/file/index.html", request.getUri());
        assertEquals("www.example.com", request.getHeaders().get("Host"));
        assertEquals("en-us", request.getHeaders().get("Accept-Language"));
    }

    @Test
    public void testParsesRequestWithBody() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(postReader);

        assertEquals("/", request.getUri());
        assertEquals("foo.com", request.getHeaders().get("Host"));
        assertEquals("application/x-wwww-form-urlencoded", request.getHeaders().get("Content-Type"));
        assertEquals("13", request.getHeaders().get("Content-Length"));
        assertEquals("say=Hi&to=Mom", request.getBody());
    }

    @Test
    public void testParsesRequestWithParams() throws IOException, MalformedRequestException {
        Request request = parser.parseRequest(paramsReader);
        String decodedParams = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff";
        assertEquals(decodedParams, request.getParams());
    }

}
