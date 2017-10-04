package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;

public class MockHandler implements Handler {

    public Response execute(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        return response;
    }
}
