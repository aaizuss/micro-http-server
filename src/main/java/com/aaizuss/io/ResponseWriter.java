package com.aaizuss.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ResponseWriter implements Writer {

    private DataOutputStream writer;
    private byte[] content;

    public void setup(Socket socket) {
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

    public void write(byte[] response) {
        try {
            writer.write(response);
            content = response;
        } catch (IOException e) {
            System.out.println("Cannot close output stream");
            e.printStackTrace();
        }
    }

    public byte[] getContent() {
        return content;
    }
}
