package Task1;

public class Task1 {
    public static void main(String[] args) {
        int beesAmount = 4;

        Forest forest = new Forest(10000);
        Bee[] bees = new Bee[beesAmount];

        for (int i = 0; i < beesAmount; i++) {
            bees[i] = new Bee(forest, "Task1.Bee" + i);
            bees[i].start();
        }
    }
}
