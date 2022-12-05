package Phaser;

public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 1 arrived");
                    phaser.arrive();
                    Thread.sleep(1000);
                    System.out.println("Thread 1 arrived again");
                    phaser.arrive();
                    System.out.println("Thread 1 arrived again again");
                    phaser.arrive();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 2 arrived");
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("Thread 2 arrived again");
                    phaser.arrive();
                    System.out.println("Thread 2 arrived again again");
                    phaser.arrive();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
