package com.aaizuss;

public class MockHandler implements Handler {
    private Request request;

    public MockHandler(Request request) {
        this.request = request;
    }

    public Response execute() {
        Response response = new Response();
        response.setStatus(Status.OK);
        return response;
    }
}
