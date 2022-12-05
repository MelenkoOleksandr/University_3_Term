package BarberShop;

import java.util.concurrent.locks.ReentrantLock;

public class Barber extends  Thread{
    BarberShop barberShop;
    ReentrantLock isBarberBusy = new ReentrantLock();

    public Barber(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    public void startHaircut() {
        isBarberBusy.lock();
    }

    public void finishHaircut() {
        isBarberBusy.unlock();
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (barberShop.hasVisitors()) {
                startHaircut();
                try {
                    barberShop.getVisitor().shave();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finishHaircut();
            }
        }
    }
}
