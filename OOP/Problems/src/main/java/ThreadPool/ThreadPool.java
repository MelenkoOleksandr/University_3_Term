package ThreadPool;

public class ThreadPool {
    Thread[] threads;
    Runnable[] tasks;
    int size;

    public ThreadPool(int size) {
        this.size = size;
        threads = new Thread[size];
        tasks = new Runnable[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Runnable task = null;
                        synchronized (tasks) {
                            while (task == null) {
                                try {
                                    tasks.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < size; i++) {
                                    if (tasks[i] != null) {
                                        task = tasks[i];
                                        tasks[i] = null;
                                        break;
                                    }
                                }
                            }
                        }
                        task.run();
                    }
                }
            });
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            for (int i = 0; i < size; i++) {
                if (tasks[i] == null) {
                    tasks[i] = task;
                    tasks.notify();
                    break;
                }
            }
        }
    }
}
