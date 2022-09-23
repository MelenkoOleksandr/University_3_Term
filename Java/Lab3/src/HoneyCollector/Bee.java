package HoneyCollector;

public class Bee extends Thread {
    final HoneyPot honeyPot;
    String beeName;
    public Bee(HoneyPot honeyPot, String beeName){
        this.honeyPot = honeyPot;
        this.beeName = beeName;
    }

    public double collectHoney() {
     return Math.random();
    }

    @Override
    public void run() {
        while (!interrupted()){
            double honey = collectHoney();
            synchronized (honeyPot) {
                if (!honeyPot.isFull()) {
                    honeyPot.addHoney(honey);
                } else {
                    System.out.println(beeName + " is going to sleep");
                    try {
                        honeyPot.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
