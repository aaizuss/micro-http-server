package com.aaizuss;

import com.aaizuss.io.socket.ServerSocketService;
import com.aaizuss.io.socket.ServerSocketWrapper;
import com.aaizuss.io.socket.SocketService;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocketService listener;
    private Router router;

    public Server(Router router) {
        this.router = router;
    }

    public void run(int port) throws IOException {
        ServerSocketService listener = setupListener(port);
        try {
            while (true) {
                SocketService clientSocket = listener.accept();
                ClientWorker clientWorker = new ClientWorker(clientSocket, router);
                ExecutorService pool = Executors.newFixedThreadPool(5);
                pool.execute(clientWorker);
            }
        } finally {
            listener.close();
        }
    }

    private ServerSocketService setupListener(int port) throws IOException {
        try {
            listener = new ServerSocketWrapper(new ServerSocket(port));
            return listener;
        } catch (BindException e) {
            e.printStackTrace();
        }
        System.out.println("returning null from setup listener!!! (this shouldn't happen)!");
        return null;
    }

}
