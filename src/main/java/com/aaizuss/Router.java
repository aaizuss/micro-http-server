package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.handler.MissingRouteHandler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;
import com.aaizuss.http.Status;

import java.util.Hashtable;

public class Router {
    private Hashtable<String,Handler> routes;

    public Router() {
        this.routes = new Hashtable<>();
    }

    public Response getResponse(Request request) {
        Handler handler = getHandler(request);
        if (handler == null) {
            handler = new MissingRouteHandler();
        }
        return handler.execute(request);
    }

    private Handler getHandler(Request request) {
        String key = createKey(request);
        return routes.get(key);
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
}
