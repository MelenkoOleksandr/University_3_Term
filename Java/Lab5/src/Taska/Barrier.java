package Taska;

public class Barrier {
    int amountOfThreads;
    int currentAmount;

    public Barrier(int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.currentAmount = amountOfThreads;
    }

    public synchronized void await() throws InterruptedException {
        if (currentAmount == 1) {
            notifyAll();
            currentAmount = amountOfThreads;
        } else {
            currentAmount--;
            this.wait();
        }
    }
}
