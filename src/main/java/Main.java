public class Main {

    public static void main(String[] args) {
        int port = 9999;
        int poolSizeThreads = 64;

        Server HTTPServer = new Server(port, poolSizeThreads);
        HTTPServer.startServer();
    }
}