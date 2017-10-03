package com.aaizuss.handler;

import com.aaizuss.*;

public class DirectoryHandler implements Handler {
    private DirectoryHtmlCreator htmlCreator;
    private String method;

    public DirectoryHandler(Request request, Directory rootDirectory) {
        this.htmlCreator = new DirectoryHtmlCreator(rootDirectory);
        this.method = request.getMethod();
    }

    public Response execute() {
        switch (method) {
            case RequestMethods.GET:
                return get();
            case RequestMethods.HEAD:
                return head();
            default:
                return new Response(Status.METHOD_NOT_ALLOWED);
        }
    }

    private byte[] getLinksAsBytes() {
        return htmlCreator.getLinkString().getBytes();
    }

    private Response get() {
        return new Response(Status.OK, getLinksAsBytes());
    }

    private Response head() {
        return new Response(Status.OK);
    }
}
