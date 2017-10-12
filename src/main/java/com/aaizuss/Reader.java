package com.aaizuss;

import java.io.IOException;

public interface Reader {
    void close();
    void read(char[] body, int offset, int len) throws IOException;
    String readLine() throws IOException;
}
