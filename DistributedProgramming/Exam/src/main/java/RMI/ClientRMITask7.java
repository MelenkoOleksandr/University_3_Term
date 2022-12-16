package RMI;

import Data.Phone;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientRMITask7 {
    private static Scanner scanner = new Scanner(System.in);
    public static void showMenu() {
        System.out.println("1. Get phones with exceeded city calls");
        System.out.println("2. Get phones used international calls");
        System.out.println("3. Get phones sorted by surname");
        System.out.println("4. Exit");
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        String url = "//localhost:123/PhoneManager";
        PhoneManager phoneManager = (PhoneManager) Naming.lookup(url);
        System.out.println("RMI object found");

        String choice = "";
        while(true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("Limit: ");
                    int limit = Integer.parseInt(scanner.nextLine());
                    ArrayList<Phone> phonesWithExceededCityCalls = phoneManager.getPhonesWithExceededCityCalls(limit);
                    System.out.println(Arrays.toString(phonesWithExceededCityCalls.toArray()));
                }
                case "2" -> {
                    ArrayList<Phone> phonesUsedInternationalCalls = phoneManager.getPhonesUsedInternationalCalls();
                    System.out.println(Arrays.toString(phonesUsedInternationalCalls.toArray()));
                }
                case "3" -> {
                    ArrayList<Phone> phonesSortedBySurname = phoneManager.getPhonesSortedBySurname();
                    System.out.println(Arrays.toString(phonesSortedBySurname.toArray()));
                }
                case "4" -> {
                    System.exit(0);
                }
                default -> System.out.println("Wrong command");
            }
        }

    }
}
