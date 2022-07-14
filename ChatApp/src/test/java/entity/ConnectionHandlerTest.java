package entity;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

class ConnectionHandlerTest {
    ServerSocket server = new ServerSocket(2030);

    ConnectionHandlerTest() throws IOException {
    }


    @Test
    void run()  {
        Assertions.assertTrue(server.isBound());
    }


    @Test
    void shutdown() throws IOException {
        server.close();
        Assert.assertTrue(server.isClosed());
    }
}