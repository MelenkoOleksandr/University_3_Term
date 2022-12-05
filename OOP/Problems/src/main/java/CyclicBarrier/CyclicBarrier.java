package CyclicBarrier;

public class CyclicBarrier {
    private int parties;
    private Runnable barrierCommand;

    public CyclicBarrier(int parties) {
        this.parties = parties;
    }

    public CyclicBarrier(int parties, Runnable barrierCommand) {
        this.parties = parties;
        this.barrierCommand = barrierCommand;
    }

    public synchronized void await() throws InterruptedException {
        parties--;
        if (parties == 0) {
            notifyAll();
            if (barrierCommand != null) {
                barrierCommand.run();
            }
        } else {
            wait();
        }
    }
}
