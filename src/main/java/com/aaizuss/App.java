package com.aaizuss;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        int port = ArgParser.getPort(args, 5000);
        Router router = setupRouter();
        Server server = new Server(router);
        server.run(port);
    }

    // ideally, a user creates an app class like this -
    // they choose a port, a directory if they want
    // and add routes to the router

    public static Router setupRouter() {
        Router router = new Router();
        // add routes
        return router;
    }

}
