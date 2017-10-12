package com.aaizuss;

import com.aaizuss.socket.ServerSocketWrapper;
import com.aaizuss.socket.SocketService;
import com.aaizuss.socket.SocketWrapper;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocketWrapper listener;
    private Router router;

    public Server(Router router) {
        this.router = router;
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html
    public void run(int port) throws IOException {
        ServerSocketWrapper listener = setupListener(port);
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

    private ServerSocketWrapper setupListener(int port) throws IOException {
        try {
            listener = new ServerSocketWrapper(new ServerSocket(port));
            return listener;
        } catch (BindException e) {
            e.printStackTrace();
        }
        System.out.println("returning null from setup listener!!! bad!");
        return null;
    }

}
