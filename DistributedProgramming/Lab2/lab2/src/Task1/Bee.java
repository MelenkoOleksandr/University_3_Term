package Task1;

import java.util.Date;

public class Bee extends Thread {
    private final Forest forest;
    private String beeName;

    public Bee(Forest forest, String beeName) {
        this.forest = forest;
        this.beeName = beeName;
    }


    @Override
    public void run() {
//        Date start = new Date();
        while (!interrupted()) {
            try {
                while (!forest.isWinnieFound()) {
                    synchronized (forest) {
                        forest.checkWinnie(beeName);
                    }
                    sleep(1);
                }
                interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        Date finish = new Date();
        //System.out.println("Thread " + beeName + " diff - " + (start.getTime() - finish.getTime()));
    }
}