package com.aaizuss;

import com.aaizuss.http.Status;
import com.aaizuss.io.Writer;
import com.aaizuss.mock.MockHandler;
import com.aaizuss.mock.MockSocket;
import com.aaizuss.io.socket.SocketService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientWorkerTest {
    private ClientWorker clientWorker;
    private static Router router = new Router();
    private String simpleGet = "GET / HTTP/1.1\n";
    private Writer writer;
    private SocketService clientSocket;

    @BeforeClass
    public static void onlyOnce() {
        router.addRoute("GET", "/", new MockHandler(Status.OK));
        router.addRoute("DELETE", "/", new MockHandler(Status.METHOD_NOT_ALLOWED));
    }


    public void setUpWorker(String request) throws IOException {
        SocketService mockSocket = new MockSocket(request);
        writer = mockSocket.getResponseWriter();
        clientWorker = new ClientWorker(mockSocket, router);
        clientSocket = mockSocket;
    }

    @Test
    public void testRunWithSimpleRequest() throws IOException {
        setUpWorker(simpleGet);
        clientWorker.run();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void testRunWithRequestWithHeaders() throws IOException {
        String headers = "Content-Type: application/x-wwww-form-urlencoded\nContent-Length: 13\r\n\r\n";
        setUpWorker(simpleGet + headers);
        clientWorker.run();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void testRunWithSimpleDeleteRequest() throws IOException {
        setUpWorker("DELETE / HTTP/1.1");
        clientWorker.run();

        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void testGracefullyRespondsWhenMissingRoute() throws IOException {
        setUpWorker("POST / HTTP/1.1");
        clientWorker.run();

        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void testGracefullyHandlesMalformedRequestLine() throws IOException {
        setUpWorker("GET/path HTTP/1.1");
        clientWorker.run();

        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void testGracefullyHandlesMalformedHeaders() throws IOException {
        String badHeaders = "Content-Type:application/x-wwww-form-urlencoded\nContent-Length: 13\r\n\r\n";
        setUpWorker(simpleGet + badHeaders);
        clientWorker.run();

        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", new String(writer.getContent()));
    }

    @Test
    public void closesClientSocket() throws IOException {
        setUpWorker(simpleGet);
        clientWorker.run();

        assertTrue(clientSocket.isClosed());
    }
}
