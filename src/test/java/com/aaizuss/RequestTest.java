package com.aaizuss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request();
    }

    @Test
    public void testSetMethod() {
        request.setMethod(RequestMethods.GET);
        assertEquals(RequestMethods.GET, request.getMethod());
    }

    @Test
    public void testSetBody() {
        request.setBody("hello");
        assertEquals("hello", request.getBody());
    }

    @Test
    public void testSetRequestTarget() {
        request.setUri("/helloworld");
        assertEquals("/helloworld", request.getUri());
    }

    @Test
    public void testSetHttpVersion() {
        request.setHttpVersion("HTTP/1.1");
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    public void testGetHeadersWithNoHeaders() {
        assertTrue(request.getHeaders().isEmpty());
    }

    @Test
    public void testGetHeader() {
        request.addHeader("Content-Type", "text/plain");
        assertEquals("text/plain", request.getHeader("Content-Type"));
    }

    @Test
    public void testGetBodyNoBody() {
        assertEquals("", request.getBody());
    }

}
