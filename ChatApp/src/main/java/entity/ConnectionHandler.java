package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ConnectionHandler implements Runnable{
    protected Socket client;
    protected BufferedReader in;
    protected PrintWriter out;
    protected String nickname;

    public ConnectionHandler(Socket client) {
        this.client = client;
    }
    public void broadcast(String message){
        for(ConnectionHandler ch: ChatServer.getConnections()){
            if(ch != null){
                ch.sendMessage(message);
            }
        }
    }
    @Override
    public void run() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println("Please enter a nickname: ");
            nickname = in.readLine();
            System.out.println(nickname + " connected!");
            broadcast(nickname + " joined the chat!");
            String message;
            while((message = in.readLine()) != null){
                if(message.startsWith("/nick")){
                    //TODO: handle change of nickname;
                    String[] messageSplit = message.split(" ",2);
                    if(messageSplit.length == 2){
                        broadcast(nickname + " changed name to "+ messageSplit[1]);
                        System.out.println(nickname + " changed name to " + messageSplit[1]);
                        nickname = messageSplit[1];
                        out.println("Successfully changed nickname to " + nickname);
                    }else{
                        out.println("No nickname provided!");
                    }
                }else if(message.startsWith("/quit")){
                    broadcast(nickname +" left the chat!");
                }else{
                    broadcast(nickname + ": "+ message);
                }
            }
        } catch (IOException e) {
            shutdown();
            System.out.println(e.getMessage());
        }
    }
    public void sendMessage(String message){
        out.println(message);
    }
    public void shutdown(){
        try{
            in.close();
            out.close();
            if(!client.isClosed()){
                client.close();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
