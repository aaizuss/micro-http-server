package com.aaizuss;

import com.aaizuss.http.Status;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientWorkerTest {
//    private ClientWorker clientWorker;
//    private Router router = new Router();
//    private String simpleGet = "GET / HTTP/1.1\n";
//    private ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//    @Before
//    public void setUp() throws IOException {
//        router.addRoute("GET", "/", new MockHandler(Status.OK));
//        Socket mockSocket = new MockSocket(simpleGet);
//        clientWorker = new ClientWorker(mockSocket, router);
//    }
//
//    @Test
//    public void testRun() {
//        System.setOut(new PrintStream(output));
//        clientWorker.run();
//
//        assertEquals("HTTP/1.1 200 OK", output.toString());
//    }
}
