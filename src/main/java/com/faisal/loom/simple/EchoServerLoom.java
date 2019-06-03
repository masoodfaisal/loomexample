package com.faisal.loom.simple;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class EchoServerLoom {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(5566);
            while (true) {
                Socket client = server.accept();
                EchoHandlerLoom handler = new EchoHandlerLoom(client);
                Fiber.schedule(handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception caught:" + e);
        }
    }
}

class EchoHandlerLoom implements Runnable {
    Socket client;

    EchoHandlerLoom(Socket client) {
        this.client = client;
    }


    @Override
    public void run()  {
        try {
            byte[] output = new java.net.URL("http://localhost:9090").openStream().readNBytes(5);
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            writer.println("[echo] " + output);
            //}
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception caught: client disconnected.");
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                ;
            }
        }

    }
}
