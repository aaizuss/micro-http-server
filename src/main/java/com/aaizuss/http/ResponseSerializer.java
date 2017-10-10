package com.aaizuss.http;

import java.util.Hashtable;
import java.util.Map;

public class ResponseSerializer {
    private static final String CRLF = "\r\n";
    private Response response;

    public ResponseSerializer(Response response) {
        this.response = response;
    }

    public byte[] getResponseBytes() {
        String header = statusLineAndHeaders();
        byte[] headerBytes = header.getBytes();
        byte[] body = response.getBody();
        byte[] response = new byte[headerBytes.length + body.length];

        System.arraycopy(headerBytes, 0, response, 0, headerBytes.length);
        System.arraycopy(body, 0, response, headerBytes.length, body.length);

        return response;
    }

    private String formatHeaders() {
        String headerString = "";
        Hashtable<String,String> headers = response.getHeaders();
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            headerString += name + ": " + value + CRLF;
        }
        return headerString + CRLF;
    }

    private String statusLineAndHeaders() {
        String statusLine = response.getStatus() + CRLF;
        String headerFields = formatHeaders();
        return statusLine + headerFields;
    }
}
