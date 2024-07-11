import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private final ExecutorService executorService;

    private Socket socket;

    public Server(int port, int poolSizeThreads) {
        this.port = port;
        executorService = Executors.newFixedThreadPool(poolSizeThreads);
    }

    public void startServer() {
        try (final var serverSocket = new ServerSocket(port)) {
            System.out.println("Server is working");
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                executorService.execute(() -> {
                    try {
                        Processor.processRequest(socket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}