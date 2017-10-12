package com.aaizuss.io;

import java.net.Socket;

public interface Writer {
    void setup(Socket socket);
    void close();
    void write(byte[] response);
    byte[] getContent();
}
