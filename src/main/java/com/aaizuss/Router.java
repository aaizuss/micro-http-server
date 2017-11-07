package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.handler.MissingRouteHandler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;

import java.util.Hashtable;

public class Router {
    private Hashtable<String,Handler> routes;
    private Handler defaultHandler;

    public Router() {
        this.routes = new Hashtable<>();
        this.defaultHandler = new MissingRouteHandler();
    }

    public Router(Handler defaultHandler) {
        this.routes = new Hashtable<>();
        this.defaultHandler = defaultHandler;
    }

    public Response getResponse(Request request) {
        Handler handler = getHandler(request);
        if (handler == null) {
            handler = defaultHandler;
        }
        return handler.execute(request);
    }

    public String createKey(String method, String uri) {
        return method + " " + uri;
    }

    public String createKey(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        return createKey(method, uri);
    }

    public void addRoute(String method, String uri, Handler handler) {
        String key = createKey(method, uri);
        routes.put(key, handler);
    }

    public Hashtable<String, Handler> getRoutes() {
        return routes;
    }

    private Handler getHandler(Request request) {
        String key = createKey(request);
        return routes.get(key);
    }
}
