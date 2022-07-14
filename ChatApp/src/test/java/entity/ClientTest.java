package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Socket client =  ClientInputHandler.getClient();



    @Test
    void run() throws IOException {
        client = new Socket("127.0.0.1", 2022);
        Assertions.assertTrue(client.isConnected());
    }
}