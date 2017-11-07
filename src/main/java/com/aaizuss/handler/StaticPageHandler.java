package com.aaizuss.handler;

import com.aaizuss.ResourceStore.ResourceStore;
import com.aaizuss.http.*;

public class StaticPageHandler extends FileContentHandler {

    private ResourceStore directory;

    public StaticPageHandler(ResourceStore directory) {
        this.directory = directory;
    }

    @Override
    protected  Response get(Request request) {
        Response response = new Response(Status.NOT_FOUND);
        if (directory.containsResource(request.getUri())) {
            response.setStatus(Status.OK);
            response.setHeader(Header.CONTENT_TYPE, FileTypeReader.getContentType(request.getUri()));
            response.setBody(directory.read(request.getUri()));
        }
        return response;
    }

    @Override
    protected  Response head(Request request) {
        Response response = new Response(Status.NOT_FOUND);
        if (directory.containsResource(request.getUri())) {
            response.setStatus(Status.OK);
            response.setHeader(Header.CONTENT_TYPE, FileTypeReader.getContentType(request.getUri()));
        }
        return response;
    }

    @Override
    protected String allowedMethods() {
        return "GET,HEAD,OPTIONS";
    }

}
