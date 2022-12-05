package ThreadPool;

public class Main {
    public static void main(String[] args) {
    ThreadPool threadPool = new ThreadPool(2);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 1");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task 2");
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 3");
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 4");
            }
        });
    }
}
