package C;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RWLock rwLock = new RWLock();
        BusFlights busFlights = new BusFlights();

//    Thread thread1 = new Thread(() -> {
//        while (!Thread.interrupted()) {
//            try {
//                rwLock.lockWrite();
//
//                rwLock.unlockWrite();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    });

        Thread thread2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    rwLock.lockRead();

                    boolean add = new Random().nextBoolean();
                    if (add) {
                        busFlights.addRoad();
                    } else {
                        busFlights.deleteRoad();
                    }

                    rwLock.unlockRead();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {

                    rwLock.lockWrite();
                    boolean add = new Random().nextBoolean();
                    if (add) {
                        busFlights.addCity();
                    } else {
                        busFlights.deleteCity();
                    }
                    rwLock.unlockWrite();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//    Thread thread4 = new Thread(() -> {
//        while (!Thread.interrupted()) {
//            try {
//                rwLock.lockWrite();
//
//                rwLock.unlockWrite();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    });

        thread2.start();
        thread3.start();
    }

}
