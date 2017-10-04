package com.aaizuss.http;

import java.util.Hashtable;
import java.util.Map;

public class ResponseFormatter {
    private static final String CRLF = "\r\n";
    private Response response;

    public ResponseFormatter(Response response) {
        this.response = response;
    }

    public String format() {
        String header = statusLineAndHeaders();
        String body = new String(response.getBody());
        return header + body;
    }

    private String statusLineAndHeaders() {
        String statusLine = response.getStatus() + CRLF;
        String headerFields = formatHeaders();
        return statusLine + headerFields;
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
}
