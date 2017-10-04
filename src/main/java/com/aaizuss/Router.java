package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;

import java.util.Hashtable;

public class Router {
    private Hashtable<String,Handler> routes; // not sure if i want values to be Handler or Response - probably handler...

    public Router() {
        this.routes = new Hashtable<>();
    }

    public Router(Hashtable<String,Handler> routes) {
        this.routes = routes;
    }

    public Handler getHandler(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        String key = method + " " + uri;
        return routes.get(key);
    }

    // theoretically could return a not found response here if the route doesn't exist
    // or i can implement that elsewhere
    public Response getResponse(Request request) {
        Handler handler = getHandler(request);
        if (handler == null) {
            return new Response(Status.NOT_FOUND);
        }
        return handler.execute();
    }

    public void addRoute(String method, String uri, Handler handler) {
        String key = method + " " + uri;
        routes.put(key, handler);
    }

    public Hashtable<String, Handler> getRoutes() {
        return routes;
    }
}
