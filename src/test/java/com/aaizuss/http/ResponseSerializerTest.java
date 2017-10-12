package com.aaizuss.http;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ResponseSerializerTest {

    private Response simpleResponse() {
        Response response = new Response(Status.OK);
        response.setHeader(Header.CONTENT_TYPE, "text/plain");
        response.setBody("response body");
        return response;
    }


    private Response minimumResponse() {
        Response response = new Response(Status.NOT_FOUND);
        return response;
    }

    private Response complexResponse() {
        Response response = new Response(Status.OK);
        response.setHeader(Header.CONTENT_TYPE, "text/plain");
        response.setHeader(Header.CONTENT_LENGTH, "13");
        response.setBody("response body");
        return response;
    }

    @Test
    public void testGetResponseBytes() {
        ResponseSerializer serializer = new ResponseSerializer(simpleResponse());
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nresponse body";

        assertEquals(expectedString, new String(serializer.getResponseBytes()));
    }

    @Test
    public void testMinimumResponse() {
        ResponseSerializer serializer = new ResponseSerializer(minimumResponse());
        String expectedString = "HTTP/1.1 404 Not Found\r\n\r\n";

        assertEquals(expectedString, new String(serializer.getResponseBytes()));
    }

    @Test
    public void testComplexResponse() {
        ResponseSerializer serializer = new ResponseSerializer(complexResponse());
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 13\r\n\r\nresponse body";

        assertEquals(expectedString, new String(serializer.getResponseBytes()));
    }
}
