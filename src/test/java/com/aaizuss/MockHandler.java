package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;
import com.aaizuss.http.Status;

public class MockHandler implements Handler {

    private String status;

    public MockHandler(String status) {
        this.status = status;
    }

    public Response execute(Request request) {
        Response response = new Response();
        response.setStatus(status);
        return response;
    }
}
