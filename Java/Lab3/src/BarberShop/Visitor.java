package BarberShop;

import java.util.concurrent.locks.ReentrantLock;

public class Visitor extends Thread{
    String name;
    boolean isWaiting = true;
    public Visitor(String name) {
        this.name = name;
    }
    public void shave() {
        isWaiting = false;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (!isWaiting) {
                System.out.println(name + " started his haircut");
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name + " Haircut is finished!");
                interrupt();
            }
        }
    }
}
