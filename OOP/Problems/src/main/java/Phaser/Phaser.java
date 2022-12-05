package Phaser;

public class Phaser {
    private int parties;
    private int phase;
    private int arrived;
    private int unarrived;
    private int terminated;

    public Phaser(int parties) {
        this.parties = parties;
        this.phase = 0;
        this.arrived = 0;
        this.unarrived = parties;
        this.terminated = 0;
    }

    public int arrive() throws InterruptedException {
        synchronized (this) {
            arrived++;
            unarrived--;
            if (arrived == parties) {
                arrived = 0;
                unarrived = parties;
                phase++;
                notifyAll();
                return phase;
            } else {
                while (arrived != 0) {
                    wait();
                }
                return phase;
            }
        }
    }

    public int arriveAndDeregister() throws InterruptedException {
        synchronized (this) {
            arrived++;
            unarrived--;
            parties--;
            if (arrived == parties) {
                arrived = 0;
                unarrived = parties;
                phase++;
                notifyAll();
                return phase;
            } else {
                while (arrived != 0) {
                    wait();
                }
                return phase;
            }
        }
    }

    public int arriveAndAwaitAdvance() throws InterruptedException {
        synchronized (this) {
            arrived++;
            unarrived--;
            if (arrived == parties) {
                arrived = 0;
                unarrived = parties;
                phase++;
                notifyAll();
                while (arrived != 0) {
                    wait();
                }
                return phase;
            } else {
                while (arrived != 0) {
                    wait();
                }
                return phase;
            }
        }
    }

    public int getPhase() {
        return phase;
    }

    public int getRegisteredParties() {
        return parties;
    }

    public int getArrivedParties() {
        return arrived;
    }
}
