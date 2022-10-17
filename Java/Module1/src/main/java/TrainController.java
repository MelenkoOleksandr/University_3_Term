import java.util.concurrent.ConcurrentLinkedQueue;

public class TrainController extends Thread {
    ConcurrentLinkedQueue<Train> firstQueue;
    ConcurrentLinkedQueue<Train> secondQueue;
    Tunnel tunnel1;
    Tunnel tunnel2;
    int maxWaitingTime;

    public void addTrainToTheFirstQueue(Train train) {
        firstQueue.add(train);
    }
    public void addTrainToTheSecondQueue(Train train) {
        secondQueue.add(train);
    }

    public TrainController(int maxWaitingTime, Tunnel tunnel1, Tunnel tunnel2) {
        this.firstQueue = new ConcurrentLinkedQueue<Train>();
        this.secondQueue  = new ConcurrentLinkedQueue<Train>();
        this.tunnel1 = tunnel1;
        this.tunnel2 = tunnel2;
        this.maxWaitingTime = maxWaitingTime;
    }

    private boolean trainsIsOver() {
        return firstQueue.isEmpty() && secondQueue.isEmpty();
    }

    private void checkSendingToAnotherTunnel() {
        for (Train train : firstQueue) {
            if (train.getWaitingTime() > maxWaitingTime) {
                secondQueue.add(train);
                train.setTunnel(tunnel2);
                train.setWaitingTime(0);
                firstQueue.remove(train);
            }
        }

        for (Train train : secondQueue) {
            if (train.getWaitingTime() > maxWaitingTime) {
                firstQueue.add(train);
                train.setTunnel(tunnel1);
                train.setWaitingTime(0);
                secondQueue.remove(train);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(firstQueue);
        System.out.println(secondQueue);
        if (firstQueue.size() != 0) {
            for (Train train: firstQueue) {
                train.start();
            }
        }
        if (secondQueue.size() != 0) {
            for (Train train : secondQueue) {
                train.start();
            }
        }

        while (!trainsIsOver()) {
            if (tunnel1.getCurrentTrain() == null && firstQueue.size() != 0) {
                Train nextTrain = firstQueue.poll();
                System.out.println(nextTrain.getTrainName() + " started moving through the tunnel 1");
                tunnel1.setCurrentTrain(nextTrain);
            }

            if (tunnel2.getCurrentTrain() == null && secondQueue.size() != 0) {
                Train nextTrain = secondQueue.poll();
                System.out.println(nextTrain.getTrainName() + " started moving through the tunnel 2");
                tunnel2.setCurrentTrain(nextTrain);
            }

            checkSendingToAnotherTunnel();
        }
    }
}
