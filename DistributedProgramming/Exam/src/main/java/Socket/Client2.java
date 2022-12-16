package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private final Scanner scanner = new Scanner(System.in);
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public Client2(String ip, int port) {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        Client2 client = new Client2("localhost", 8080);
//        client.showMenu();
        while (true) {
            client.sendQuery("getPhonesUsedInternationalCalls");
            String response = client.in.readLine();
            while (!response.equals("end")) {
                System.out.println(response);
                response = client.in.readLine();
            }
            Thread.sleep(2000);
        }
    }
}
