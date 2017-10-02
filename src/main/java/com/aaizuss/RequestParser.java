package com.aaizuss;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class RequestParser {
    private Request request;

    public Request parseRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(request, getHeaders(reader));
        if (hasBody(request)) {
            parseBody(request, reader);
        }
        return request;
    }

    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine != null) {
            String[] parts = requestLine.split(" ");
            String method = parts[0];
            String uri = parts[1];
            String httpVersion = parts[2];
            if (hasParams(uri)) {
                request = createRequestWithParams(method, uri, httpVersion);
            } else {
                request = new Request(method, uri, httpVersion);
            }
        }
    }

    private ArrayList<String> getHeaders(BufferedReader reader) throws IOException {
        ArrayList<String> headerLines = new ArrayList<>();
        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            if(line.isEmpty()) {
                break;
            }
            headerLines.add(line);
        }
        return headerLines;
    }

    private void parseHeaders(Request request, ArrayList<String> headers) {
        for (String header : headers) {
            String[] pair = header.split(": ");
            String key = pair[0];
            String value = pair[1];
            request.addHeader(key, value);
        }
    }

    private void parseBody(Request request, BufferedReader reader) throws IOException {
        Hashtable<String, String> headers = request.getHeaders();
        int contentLength = Integer.parseInt(headers.get(Header.CONTENT_LENGTH));
        char[] body = new char[contentLength];
        reader.read(body, 0, contentLength);
        String bodyString = new String(body);
        request.setBody(bodyString);
    }

    private Request createRequestWithParams(String method, String uri, String httpVersion) {
        int paramStart = uri.indexOf("?");
        String resourcePath = uri.substring(0, paramStart);
        String query = uri.substring(paramStart + 1);
        String params = ParameterDecoder.decode(query);
        return new Request(method, resourcePath, params, httpVersion);
    }

    private boolean hasParams(String uri) {
        return uri.contains("?");
    }

    private boolean hasBody(Request request) {
        return request.getHeaders().containsKey(Header.CONTENT_LENGTH);
    }

}
