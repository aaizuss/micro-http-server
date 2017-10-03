package com.aaizuss;

import java.io.IOException;

// i think a user should create an app class like this
// they choose a port, a directory if they want
// and add routes to the router

// OR i can provide a bunch of methods in this App class for adding routes, and then to make an app
// with this server, a user instantiates an App and calls App.run() (run would set up the server and run it)
public class App {

    public static void main(String[] args) throws IOException {
        int port = ArgParser.getPort(args, 5000);
        Router router = setupRouter();
        Server server = new Server(router);
        server.run(port);
    }

    public static Router setupRouter() {
        Router router = new Router();
        // add routes
        return router;
    }

}
