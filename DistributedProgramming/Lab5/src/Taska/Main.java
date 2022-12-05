package Taska;

import java.util.ArrayList;

public class Main {

    public static void printSoldiers(ArrayList<Boolean> soldiers) {
        System.out.println("Soldiers");
        for (int i = 0; i < soldiers.size(); i++) {
            System.out.print(soldiers.get(i) ? "->  " : "<-  ");
        }
        System.out.println();
    }

    public static boolean isStateStable(ArrayList<Boolean> prev, ArrayList<Boolean> cur) {
        for (int i =0; i < prev.size(); i++) {
            if (prev.get(i) != cur.get(i)) {
                return  false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // false - left, true - rigth
        ArrayList<Boolean> soldiers = new ArrayList<>();
        Barrier barrier = new Barrier(2);

        for (int i = 0; i < 100; i++) {
            soldiers.add(i, Math.random() < 0.5);
        }


        Thread t1 = new Thread(() -> {
            ArrayList<Boolean> prevState = new ArrayList<>(soldiers);
            while (!Thread.currentThread().isInterrupted()) {
                for(int i = 0; i < 50; i++ ) {
                    synchronized (soldiers) {
                        if (soldiers.get(i) != soldiers.get(i + 1)) {
                            soldiers.set(i, !soldiers.get(i));
                            soldiers.set(i + 1, !soldiers.get(i+1));
                        }
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                printSoldiers(soldiers);
                if (isStateStable(prevState, soldiers)) {
                    System.out.println("Soldiers stable!");
                    break;
                } else {
                    prevState = new ArrayList<>(soldiers);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            ArrayList<Boolean> prevState = new ArrayList<>(soldiers);
            while (!Thread.currentThread().isInterrupted()) {
                for(int i = 50; i < 99; i++ ) {
                    if (soldiers.get(i) != soldiers.get(i + 1)) {
                        soldiers.set(i, !soldiers.get(i));
                        soldiers.set(i + 1, !soldiers.get(i+1));
                    }
                }

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isStateStable(prevState, soldiers)) {
                    System.out.println("Soldiers stable!");
                    break;
                } else {
                    prevState = new ArrayList<>(soldiers);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
