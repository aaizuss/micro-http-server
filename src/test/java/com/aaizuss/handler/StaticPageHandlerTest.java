package com.aaizuss.handler;

import com.aaizuss.ResourceStore.MockFileDirectory;
import com.aaizuss.http.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StaticPageHandlerTest {
    private static MockFileDirectory mockFileDirectory;
    private StaticPageHandler handler;
    private Request request = new Request(RequestMethods.GET, "/index.html");

    @BeforeClass
    public static void onlyOnce() {
        mockFileDirectory = MockFileDirectory.withTextFile("/", "index.html", "<p>hello world</p>\n");
    }

    @Before
    public void setUp() throws IOException {
        handler = new StaticPageHandler(mockFileDirectory);
    }

    @Test
    public void testReturnsNotFoundWhenResourceDoesNotExist() {
        Request request = new Request(RequestMethods.GET, "/foo");
        Response response = handler.execute(request);

        assertEquals(Status.NOT_FOUND, response.getStatus());
    }

    @Test
    public void givenRequestForExistingFileReturnsOkAndFillsBodyWithContent() {
        Response response = handler.execute(request);
        String content = "<p>hello world</p>\n";
        assertEquals(Status.OK, response.getStatus());
        assertEquals("text/html", response.getHeader(Header.CONTENT_TYPE));
        assertEquals(content, new String(response.getBody()));
    }

    @Test
    public void headRequestHasNoBody() {
        request.setMethod(RequestMethods.HEAD);
        Response response = handler.execute(request);
        assertEquals(Status.OK, response.getStatus());
        assertEquals("text/html", response.getHeader(Header.CONTENT_TYPE));
        assertEquals("", new String(response.getBody()));
    }
}
