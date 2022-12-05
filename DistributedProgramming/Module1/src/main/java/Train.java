public class Train extends Thread{
    private final String trainName;
    private Tunnel tunnel;
    private final int speed;
    private int distance;
    private int waitingTime;
    private boolean passedTunnel;

    public Train( String trainName, Tunnel tunnel, int speed) {
        this.trainName = trainName;
        this.tunnel = tunnel;
        this.speed = speed;
        this.waitingTime = 0;
        this.distance = 0;
        passedTunnel = false;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!passedTunnel) {
            Thread.onSpinWait();
            Train currentTrain = tunnel.getCurrentTrain();
            if (currentTrain == this) {
                waitingTime = 0;
                distance += speed;
                if (distance > tunnel.getTunnelLength()) {
                    System.out.println(trainName + " passed the tunnel");
                    tunnel.setCurrentTrain(null);
                    passedTunnel = true;
                }
            } else {
                waitingTime++;
            }
        }
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTunnel(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    public Tunnel getTunnel() {
        return tunnel;
    }

    public String getTrainName() {
        return trainName;
    }
}
