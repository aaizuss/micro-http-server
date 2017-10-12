package com.aaizuss.socket;

import com.aaizuss.Reader;
import com.aaizuss.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SocketService {
    public void close() throws IOException;
    Writer getResponseWriter();
    Reader getRequestReader();
    public OutputStream getOutputStream() throws IOException;
    public InputStream getInputStream() throws IOException;
    public boolean isClosed();
}
