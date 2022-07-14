package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientInputHandler implements Runnable{
    private boolean done;
    private static Socket client;

    public static Socket getClient() {
        return client;
    }

    private BufferedReader in = Client.getIn();
    private PrintWriter out = Client.getOut();
    public void shutdown(){
        done = true;
        try {
            in.close();
            out.close();
            if(!client.isClosed()){
                client.close();
            }
        }catch (IOException e){
            //TODO: custom exceptions
        }

    }

    @Override
    public void run() {
        try{
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            while(!done){
                String message = inReader.readLine();
                if(message.equals("/quit")){
                    out.println(message);
                    inReader.close();
                    shutdown();
                }else{
                    out.println(message);
                }
            }
        }catch (IOException e){
            //TODO
            shutdown();
        }
    }
}
