package HoneyCollector;

public class HoneyPot {
    double capacity = 10;
    double fullness = 0;

    public void addHoney(double honey) {
        if (this.fullness + honey < capacity) {
            this.fullness += honey;
            System.out.println("Honey pot has " + fullness + " out of " + capacity);
        } else {
            this.fullness = this.capacity;
        }
    }

    public void eatHoney() {
        System.out.println("Eating honey...");
        this.fullness = 0;
    }

    public boolean isFull() {
        return capacity == fullness;
    }
}
