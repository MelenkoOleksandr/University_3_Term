package Task2;

import java.util.concurrent.LinkedBlockingDeque;

public class Officers {
    private static int storage = 100000;
    private static LinkedBlockingDeque stolen = new LinkedBlockingDeque();
    private static LinkedBlockingDeque loaded = new LinkedBlockingDeque();

    public  void startBusiness() {
        //Краде якщо є шо красти
        new Thread(() -> {
            while (storage > 0) {
                int steal = (int) (Math.random() * 100 + 1);
                System.out.println("Іванов виніс: " + steal);
                stolen.addFirst(steal);
                // Відпочинок між злочинами
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //Вантажить якщо шось вкрадене
        new Thread(() -> {
            while (true) {
                    if (stolen.size() > 0) {
                        int stealCount = (int) stolen.pollLast();
                        System.out.println("Петров завантажив: " + stealCount);
                        loaded.addFirst(stealCount);
                    }
            }
        }).start();

        //Рахує вкрадене
        new Thread(() -> {
            while (true) {
                    if (loaded.size() > 0) {
                        int stealCount = (int) loaded.pollLast();
                        System.out.println("Нечипорчук підрахував вартість: " + stealCount);
                    }
                }
        }).start();
    }
}
