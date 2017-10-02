package com.aaizuss;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class ResponseSerializerTest {

    @Test
    public void testGetResponseBytes() {
        byte[] body = "body text".getBytes();
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put(Header.CONTENT_TYPE, "text/plain");

        Response response = new Response(Status.OK, headers, body);
        ResponseSerializer serializer = new ResponseSerializer(response);
        String bodyString = new String(body);
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n" + bodyString;

        assertEquals(expectedString, new String(serializer.getResponseBytes()));
    }
}
