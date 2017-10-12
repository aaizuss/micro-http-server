package com.aaizuss.mock;

import com.aaizuss.Writer;
import com.aaizuss.http.Response;
import com.aaizuss.http.ResponseSerializer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MockResponseWriter implements Writer {
    private DataOutputStream writer;
    private byte[] responseBytes;

    public MockResponseWriter() {
        writer = new DataOutputStream(new ByteArrayOutputStream());
    }

    public MockResponseWriter(Response response) {
        this.responseBytes = new ResponseSerializer(response).getResponseBytes();
    }

    public void setup(Socket socket) {

    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot close output stream");
            e.printStackTrace();
        }
    }

    public byte[] getContent() {
        return responseBytes;
    }

    public void write(byte[] response) {
        try {
            writer.write(response);
            responseBytes = response;
        } catch (IOException e) {
            System.out.println("Cannot close output stream");
            e.printStackTrace();
        }
    }
}
