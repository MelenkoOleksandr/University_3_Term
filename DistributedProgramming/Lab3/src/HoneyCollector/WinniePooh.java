package HoneyCollector;

public class WinniePooh extends Thread {
    final HoneyPot honeyPot;
    public WinniePooh(HoneyPot honeyPot){
        this.honeyPot = honeyPot;
    }

    @Override
    public void run() {
        while (!interrupted()){
            synchronized (honeyPot) {
                if (honeyPot.isFull()) {
                    honeyPot.eatHoney();
                    honeyPot.notifyAll();
                }
            }
        }
    }
}
