package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class Server {

    private static final int SHUTDOWN_WAIT_TIME_MS = 800;
    private final int port;
    private final ExecutorService pool;
    private final ConcurrentHashMap<String, String> quotes;
    private ServerSocket serverSocket;
    private volatile boolean running = true;

    public Server(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
        this.quotes = new ConcurrentHashMap<>();
        initializeQuotes();
    }

    private void initializeQuotes() {
        quotes.put("личности", "Не переходи на личности там, где их нет");
        quotes.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        quotes.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        quotes.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server stopped");
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
            pool.shutdown();
            if (!pool.awaitTermination(SHUTDOWN_WAIT_TIME_MS, TimeUnit.MILLISECONDS)) {
                pool.shutdownNow();
            }
        } catch (IOException | InterruptedException e) {
            pool.shutdownNow();
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String response = quotes.getOrDefault(inputLine, "Цитата не найдена");
                    out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
