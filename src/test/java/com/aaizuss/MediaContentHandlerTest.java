package com.aaizuss;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MediaContentHandlerTest {
    private Directory directory;
    private MediaContentHandler handler;
    private Request pngRequest = new Request("GET", "/image.png");

    @Before
    public void setUp() throws IOException, DirectoryNotFoundException {
        directory = MockDirectory.get();
    }

    @Test
    public void testResponseHeaderAndStatus() {
        handler = new MediaContentHandler(pngRequest, directory);
        Response response = handler.execute();
        assertEquals(Status.OK, response.getStatus());
        assertEquals("image/png", response.getHeader(Header.CONTENT_TYPE));
    }

    @Test
    public void testPostRequestReturnsMethodNotAllowed() {
        Request postRequest = new Request("POST", "/image.png");
        handler = new MediaContentHandler(postRequest, directory);
        Response response = handler.execute();
        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }
}
