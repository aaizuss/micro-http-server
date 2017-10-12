package com.aaizuss;

import com.aaizuss.http.*;
import com.aaizuss.socket.SocketService;
import java.io.IOException;

public class ClientWorker implements Runnable {

    private SocketService socket;
    private Reader reader;
    private Writer writer;
    private Router router;

    public ClientWorker(SocketService clientSocket, Router router) {
        this.socket = clientSocket;
        this.writer = clientSocket.getResponseWriter();
        this.reader = clientSocket.getRequestReader();
        this.router = router;
    }

    public void run() {
        try {
            Request request = buildRequestFromInput();
            respondToRequest(request);
            closeIO();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedRequestException e) {
            respondToMalformedRequest();
            closeIO();
        } finally {
            closeSocket();
        }
    }

    private void respondToRequest(Request request) {
        Response response = router.getResponse(request);
        ResponseSerializer serializer = new ResponseSerializer(response);
        writer.write(serializer.getResponseBytes());
    }

    private void respondToMalformedRequest() {
        Response response = new Response(Status.BAD_REQUEST);
        ResponseSerializer serializer = new ResponseSerializer(response);
        writer.write(serializer.getResponseBytes());
    }

    private Request buildRequestFromInput() throws IOException, MalformedRequestException {
        RequestParser parser = new RequestParser();
        return parser.parseRequest(reader);
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Unable to close client socket");
            e.printStackTrace();
        }
    }

    private void closeIO() {
        writer.close();
        reader.close();
    }
}
