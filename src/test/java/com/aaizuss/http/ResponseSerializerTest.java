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

    @Test
    public void testGetResponseBytes() {
        ResponseSerializer serializer = new ResponseSerializer(simpleResponse());
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nresponse body";

        assertEquals(expectedString, new String(serializer.getResponseBytes()));
    }
}
