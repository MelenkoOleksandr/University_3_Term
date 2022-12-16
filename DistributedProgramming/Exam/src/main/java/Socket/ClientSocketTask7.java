package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketTask7 {
    private final Scanner scanner = new Scanner(System.in);
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public ClientSocketTask7(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void sendQuery(String query) {
        out.println(query);
    }

    public void showMenu() {
        System.out.println("1. Get phones with exceeded city calls");
        System.out.println("2. Get phones used international calls");
        System.out.println("3. Get phones sorted by surname");
        System.out.println("4. Exit");
    }

    public static void main(String[] args) throws IOException {
        ClientSocketTask7 client = new ClientSocketTask7("localhost", 8080);
        client.showMenu();
        while (true) {
            System.out.print("Enter command: ");
            String command = client.scanner.nextLine();
            switch (command) {
                case "1" -> {
                    System.out.print("Enter limit: ");
                    String limit = client.scanner.nextLine();
                    client.sendQuery("getPhonesWithExceededCityCalls%" + limit);
                }
                case "2" -> client.sendQuery("getPhonesUsedInternationalCalls");
                case "3" -> client.sendQuery("getPhonesSortedBySurname");
                case "4" -> {
                    client.sendQuery("exit");
                    return;
                }
                default -> System.out.println("Unknown command");
            }
            String response = client.in.readLine();
            while (!response.equals("end")) {
                System.out.println(response);
                response = client.in.readLine();
            }
        }
    }
}
