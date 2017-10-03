package com.aaizuss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private ResponseWriter writer;
    private Router router;

    public ClientWorker(Socket clientSocket, Router router) {
        this.socket = clientSocket;
        this.writer = new ResponseWriter(socket);
        this.router = router;
        setupRequestReader();
    }

    public void run() {
        try {
            Request request = buildRequestFromInput();
            respondToRequest(request);
            closeIO();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSocket();
        }
    }

    private void respondToRequest(Request request) {
        // todo: add class for creating/changing resources to support PUT, POST, DELETE
        Response response = router.getResponse(request);
        ResponseSerializer serializer = new ResponseSerializer(response);
        writer.write(serializer.getResponseBytes());
    }

    private Request buildRequestFromInput() throws IOException {
        RequestParser parser = new RequestParser();
        return parser.parseRequest(reader);
    }

    private void setupRequestReader() {
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Cannot get input stream");
            e.printStackTrace();
        }
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
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Cannot close request reader");
            e.printStackTrace();
        }
    }
}
