package BarberShop;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BarberShop barberShop = new BarberShop();
        Barber barber = new Barber(barberShop);

        int i = 1;
        barber.start();
        while (i < 1000) {
            Visitor visitor = new Visitor("Visitor" + i);
            visitor.start();
            barberShop.standInQueue(visitor);
            i++;
            Thread.sleep(300);
        }
    }
}
