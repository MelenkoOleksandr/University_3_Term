public class task7Java {
    public static void main(String[] args) {
        Tunnel tunnel1 = new Tunnel();
        Tunnel tunnel2 = new Tunnel();
        TrainController trainController = new TrainController(10, tunnel1, tunnel2);


        for (int i = 0; i < 50; i++) {
            Train train = new Train("Train" + i, i % 2 == 0 ? tunnel1 : tunnel2, (int) (Math.random() * 100) + 10);
            if (i % 2 == 0) {
                trainController.addTrainToTheFirstQueue(train);
            } else {
                trainController.addTrainToTheSecondQueue(train);
            }
        }

        trainController.start();
    }
}
