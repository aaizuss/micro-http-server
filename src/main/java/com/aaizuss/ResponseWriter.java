package com.aaizuss;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ResponseWriter {

    private DataOutputStream writer;

    public ResponseWriter(Socket socket) {
        try {
            writer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Can't open output stream");
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot close output stream");
            e.printStackTrace();
        }
    }

    // not sure what the format will be yet...
    public void write(byte[] response) {
        try {
            writer.write(response);
        } catch (IOException e) {
            System.out.println("Cannot close output stream");
            e.printStackTrace();
        }
    }
}