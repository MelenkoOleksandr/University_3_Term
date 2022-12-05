import java.util.ArrayList;

public class Tunnel {

    private Train currentTrain;
    private final int tunnelLength;

    public Tunnel() {
        //Min - 100, Max - 500
        tunnelLength = (int) (Math.random() * 400 + 100);
        currentTrain = null;
    }

    public Train getCurrentTrain() {
        return currentTrain;
    }

    public void setCurrentTrain(Train currentTrain) {
        this.currentTrain = currentTrain;
    }

    public int getTunnelLength() {
        return tunnelLength;
    }

}
