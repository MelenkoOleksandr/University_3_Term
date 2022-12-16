package Socket;

import Data.PhoneDB;
import Helpers.SocketHelper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTask7 {
    private static PhoneDB phoneDB = new PhoneDB();
    private ServerSocket serverSocket = null;

    private final int PORT = 8080;



    public void listen() throws IOException {
        serverSocket = new ServerSocket(PORT);
        serverSocket.setReuseAddress(true);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected"
                    + clientSocket.getInetAddress()
                    .getHostAddress());
            ClientHandler clientHandler = new ClientHandler(clientSocket, phoneDB);
            clientHandler.start();
        }
    }



    public static void main(String[] args) {
        try {
            phoneDB.fillDB();
            new ServerSocketTask7().listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private static PhoneDB phoneDB;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ClientHandler(Socket socket, PhoneDB phoneDB) {
        this.socket = socket;
        this.phoneDB = phoneDB;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while (processQuery());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(String... args) {
        out.println(SocketHelper.generateLineWithSeparator(args));
    }

    public boolean processQuery() {
        try {
            String query = in.readLine();
            String[] queryParts = query.split("%");
            String command = queryParts[0];
            switch (command) {
                case "getPhonesWithExceededCityCalls" -> {
                    int limit = Integer.parseInt(queryParts[1]);
                    phoneDB.getPhonesWithExceededCityCalls(limit).forEach(phone -> sendResponse(phone.toString()));
                    out.println("end");
                }
                case "getPhonesUsedInternationalCalls" -> {
                    phoneDB.getPhonesUsedInternationalCalls().forEach(phone -> sendResponse(phone.toString()));
                    out.println("end");
                }
                case "getPhonesSortedBySurname" -> {
                    phoneDB.getPhonesSortedBySurname().forEach(phone -> sendResponse(phone.toString()));
                    out.println("end");
                }
                default -> sendResponse("Unknown command");
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}