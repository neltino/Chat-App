package entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements Runnable{
    private static ArrayList<ConnectionHandler> connections;

    public static ArrayList<ConnectionHandler> getConnections() {
        return connections;
    }

    private ServerSocket server;
    boolean done;
    private ExecutorService pool;
    public ChatServer(){
        connections = new ArrayList<>();
        done = false;
    }
    @Override
    public void run() {
        try {
            server = new ServerSocket(2022);
            while(!done){
                Socket client = server.accept();
                pool = Executors.newCachedThreadPool();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            try {
                shutdown();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public void shutdown() throws IOException {
        done = true;
        pool.shutdown();
        if(!server.isClosed()){
            server.close();
        }
        for(ConnectionHandler ch : connections){
            ch.shutdown();
        }
    }


    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.run();
    }
}

