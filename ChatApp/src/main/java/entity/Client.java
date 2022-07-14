package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private static BufferedReader in;
    public static BufferedReader getIn() {
        return in;
    }
    public static PrintWriter getOut() {
        return out;
    }
    private static PrintWriter out;
    private Socket client = ClientInputHandler.getClient();

    @Override
    public void run() {
        try{
            client = new Socket("127.0.0.1", 2022);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            ClientInputHandler clientInputHandler = new ClientInputHandler();
            Thread t = new Thread(clientInputHandler);
            t.start();
            String inMessage;
            while((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
            }
        }catch (IOException e){
            //TODO: exception handler
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
