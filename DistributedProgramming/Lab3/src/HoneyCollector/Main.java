package HoneyCollector;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HoneyPot honeyPot = new HoneyPot();
        ArrayList<Bee> bees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount of bees:");
        int amountOfBees = scanner.nextInt();
        for (int i = 0; i < amountOfBees; i++) {
            bees.add( new Bee(honeyPot, "HoneyCollector.Bee" + (i + 1)));
        }
        WinniePooh winniePooh = new WinniePooh(honeyPot);
        for (Bee bee : bees) {
            bee.start();
        }
        winniePooh.start();
    }
}
