package com.aaizuss.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketService {
    private ServerSocket serverSocket;

    public ServerSocketWrapper(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public SocketService accept() throws IOException {
        return new SocketWrapper(serverSocket.accept());
    }

    public SocketService accept(SocketService socketService) {
        return new SocketWrapper((Socket) socketService);
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public boolean isClosed() throws IOException {
        return serverSocket.isClosed();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
