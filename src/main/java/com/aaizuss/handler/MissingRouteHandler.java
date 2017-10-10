package com.aaizuss.handler;

import com.aaizuss.http.Status;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;

public class MissingRouteHandler implements Handler {

    @Override
    public Response execute(Request request) {
        return new Response(Status.NOT_FOUND);
    }
}
