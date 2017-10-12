package com.aaizuss.mock;

import com.aaizuss.io.Reader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MockRequestReader implements Reader {
    private BufferedReader reader;

    public MockRequestReader(String rawRequest) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes());
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void close() {
        try {
            reader.close();
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
