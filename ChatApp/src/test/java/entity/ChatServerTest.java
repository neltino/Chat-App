package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.ServerSocket;


class ChatServerTest {


    ServerSocket server = new ServerSocket(2020);

    ChatServerTest() throws IOException {
    }


    @org.junit.jupiter.api.Test
    void run() {
        Assertions.assertTrue(server.isBound());
    }

    @org.junit.jupiter.api.Test
    void shutdown() throws IOException {
        server.close();
        Assertions.assertTrue(server.isClosed());
    }
}