package com.aaizuss.http;

import com.aaizuss.Header;
import com.aaizuss.Status;
import com.aaizuss.http.Response;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseTest {
    private byte[] basicBody = "body text".getBytes();

    @Test
    public void testSimpleResponseConstructorAndGetters() {
        Response response = new Response(Status.OK, basicBody);

        assertEquals(basicBody, response.getBody());
        assertEquals(Status.OK, response.getStatus());
    }

    @Test
    public void testSetters() {
        Response response = new Response(Status.OK, basicBody);
        response.setStatus(Status.NOT_FOUND);
        response.setBody("different body".getBytes());

        assertEquals(Status.NOT_FOUND, response.getStatus());
        assertEquals("different body", new String(response.getBody()));
    }

    @Test
    public void testConstructorWithHeaders() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put(Header.CONTENT_TYPE, "text/plain");

        Response response = new Response(Status.OK, headers, basicBody);
        assertTrue(response.getHeaders().containsKey(Header.CONTENT_TYPE));
    }
}
