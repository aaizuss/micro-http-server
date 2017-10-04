package com.aaizuss.http;

import com.aaizuss.Header;
import com.aaizuss.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseFormatterTest {

    private Response simpleResponse() {
        Response response = new Response(Status.OK);
        response.setHeader(Header.CONTENT_TYPE, "text/plain");
        response.setBody("response body");
        return response;
    }

    @Test
    public void testFormat() {
        ResponseFormatter formatter = new ResponseFormatter(simpleResponse());
        String expectedString = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nresponse body";
        assertEquals(expectedString, formatter.format());
    }
}
