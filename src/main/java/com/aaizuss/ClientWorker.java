package com.aaizuss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private ResponseWriter writer;

    public ClientWorker(Socket clientSocket) {
        socket = clientSocket;
        this.writer = new ResponseWriter(socket);
        setupRequestReader();
    }

    public void run() {
        try {
            Request request = buildRequestFromInput();
            // handle the request (write response)
            closeIO();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSocket();
        }
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
