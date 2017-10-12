package com.aaizuss.io.socket;

import com.aaizuss.io.Reader;
import com.aaizuss.io.RequestReader;
import com.aaizuss.io.ResponseWriter;
import com.aaizuss.io.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWrapper implements SocketService {
    private Socket socket;

    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }

    public Writer getResponseWriter() {
        ResponseWriter writer = new ResponseWriter();
        writer.setup(socket);
        return writer;
    }

    public Reader getRequestReader() {
        RequestReader reader = new RequestReader(socket);
        return reader;
    }

    public void close() throws IOException {
        socket.close();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public boolean isClosed() {
        return socket.isClosed();
    }
}
