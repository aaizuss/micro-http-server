package com.aaizuss.handler;

import com.aaizuss.http.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileContentHandlerTest {

    private FileContentHandler handler = new FileContentHandler() {
        @Override
        protected String allowedMethods() {
            return "";
        }
    };

    private Response responseForMethod(String method) {
        Request request = new Request(method, "/foo");
        return handler.execute(request);
    }

    @Test
    public void givenGetRequestItReturnsNotAllowedResponse() {
        Response response = responseForMethod("GET");
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

    @Test
    public void givenHeadRequestItReturnsNotAllowedResponse() {
        Response response = responseForMethod("HEAD");
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

    @Test
    public void givenPostRequestItReturnsNotAllowedResponse() {
        Response response = responseForMethod("POST");
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

    @Test
    public void givenPutRequestItReturnsNotAllowedResponse() {
        Response response = responseForMethod("PUT");
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

    @Test
    public void givenDeleteRequestItReturnsNotAllowedResponse() {
        Response response = responseForMethod("DELETE");
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

    @Test
    public void givenOptionsRequestItReturnsEmptyString() {
        Response response = responseForMethod("OPTIONS");
        assertEquals(Status.OK, response.getStatus());
        assertEquals("", response.getHeader(Header.ALLOW));
    }
}
