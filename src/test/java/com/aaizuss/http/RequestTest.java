package com.aaizuss.http;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void testGetContentRange() {
        request.addHeader("Range", "bytes=10-25/50");
        Hashtable<String,Integer> contentRange = request.getContentRange();
        int start = contentRange.get("Start");
        int end = contentRange.get("End");
        assertEquals(10, start);
        assertEquals(25, end);
    }

    @Test
    public void testGetContentRangeNoRange() {
        Hashtable<String,Integer> contentRange = request.getContentRange();
        assertTrue(contentRange.isEmpty());
        assertFalse(request.isPartial());
    }

    @Test
    public void testIsPartial() {
        request.addHeader("Range", "bytes=10-25/50");

        assertTrue(request.isPartial());
    }
}
