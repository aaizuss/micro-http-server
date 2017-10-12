package com.aaizuss.socket;

import java.io.IOException;

public interface ServerSocketService {
    public SocketService accept() throws IOException;
    public void close() throws IOException;
    public boolean isClosed() throws IOException;
}
