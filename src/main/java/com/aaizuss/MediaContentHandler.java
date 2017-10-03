package com.aaizuss;

public class MediaContentHandler implements Handler {
    private Request request;
    private Directory directory;

    public MediaContentHandler(Request request, Directory directory) {
        this.request = request;
        this.directory = directory;
    }

    public Response execute() {
        if (supportsRequest()) {
            Response response = new Response(Status.OK);
            response.setBody(ResourceReader.getContent(request.getUri(), directory));
            response.setHeader(Header.CONTENT_TYPE, ResourceReader.getContentType(request.getUri()));
            return response;
        } else {
            return notAllowedResponse();
        }
    }

    private Response notAllowedResponse() {
        return new Response(Status.METHOD_NOT_ALLOWED);
    }

    private boolean supportsRequest() {
        return request.getMethod().equals(RequestMethods.GET);
    }
}
