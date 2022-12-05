package Taskb;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        Thread t1 = new Swapper(cyclicBarrier, monitor, "ABAAACDDC", 0);
        Thread t2 = new Swapper(cyclicBarrier, monitor, "CDACCBBAA", 1);
        Thread t3 = new Swapper(cyclicBarrier, monitor, "ABCCCDCDD", 2);
        Thread t4 = new Swapper(cyclicBarrier, monitor, "DAADDABBD", 3);

        t1.setDaemon(true);
        t2.setDaemon(true);
        t3.setDaemon(true);
        t4.setDaemon(true);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
