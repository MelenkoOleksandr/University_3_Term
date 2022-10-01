package C;

import java.util.ArrayList;

public class Main {
    RWLock rwLock = new RWLock();

    Thread thread1 = new Thread(() -> {
        while (!Thread.interrupted()) {
            try {
                rwLock.lockWrite();

                rwLock.unlockWrite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    });

    Thread thread2 = new Thread(() -> {
        while (!Thread.interrupted()) {
            try {
                rwLock.lockWrite();

                rwLock.unlockWrite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

    Thread thread3 = new Thread(() -> {
        while (!Thread.interrupted()) {
            try {
                rwLock.lockWrite();

                rwLock.unlockWrite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

    Thread thread4 = new Thread(() -> {
        while (!Thread.interrupted()) {
            try {
                rwLock.lockWrite();

                rwLock.unlockWrite();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });
}
