package com.aaizuss.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader implements Reader {
    private BufferedReader reader;
    private Socket socket;

    public RequestReader(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Can't open input stream");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            reader.close();
            // socket.close(); don't know if this should happen here
        } catch (IOException e) {
            System.err.println("Cannot close request reader");
            e.printStackTrace();
        }
    }

    public void read(char[] body, int offset, int len) throws IOException {
        reader.read(body, offset, len);
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }
}
