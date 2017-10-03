package com.aaizuss.handler;

import com.aaizuss.*;
import com.aaizuss.exception.DirectoryNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DirectoryHandlerTest {
    private Directory directory;
    private DirectoryHandler handler;
    private Request request = new Request("GET", "/");

    @Before
    public void setUp() throws DirectoryNotFoundException, IOException {
        directory = new Directory(System.getProperty("user.dir") + "/test-directory/");
        handler = new DirectoryHandler(request, directory);
    }

    @Test
    public void testDirectoryResponse() {
        Response response = handler.execute();
        String expectedBody = "<a href='/empty-folder'>empty-folder</a></br>\r\n" +
                "<a href='/puppies'>puppies</a></br>\r\n" +
                "<a href='/text-file.txt'>text-file.txt</a></br>\r\n";
        assertEquals(Status.OK, response.getStatus());
        assertEquals(expectedBody, new String(response.getBody()));

    }
}
