package ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("Thread 1 locked");
//                    lock.lock();
//                    System.out.println("Thread 1 locked again");
//                    lock.unlock();
//                    System.out.println("Thread 1 unlocked");
                    lock.unlock();
                    System.out.println("Thread 1 unlocked ");
                    lock.lock();
                    System.out.println("Thread 1 locked again");
                    lock.unlock();
                    System.out.println("Thread 1 unlocked again");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("Thread 2 locked");
                    lock.lock();
                    System.out.println("Thread 2 locked again");
                    lock.unlock();
                    System.out.println("Thread 2 unlocked");
                    lock.unlock();
                    System.out.println("Thread 2 unlocked again");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
