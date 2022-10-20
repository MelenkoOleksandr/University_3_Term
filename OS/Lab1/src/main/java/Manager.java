import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Manager {
    final PipedOutputStream f_output;
    final PipedOutputStream g_output;
    final PipedInputStream f_input;
    final PipedInputStream g_input;

    public Manager()  {
        try {
            f_output = new PipedOutputStream();
            g_output = new PipedOutputStream();
            f_input = new PipedInputStream(f_output);
            g_input = new PipedInputStream(g_output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showModal() {
        System.out.println("(a) continue");
        System.out.println("(b) continue without prompt");
        System.out.println("(c) stop");
    }

    public StringBuilder getPipeResult(char pipe) throws IOException {
        StringBuilder result = new StringBuilder();
        int currentByte = pipe == 'F' ? f_input.read() : g_input.read();
        while (currentByte != -1) {
            result.append((char)currentByte);
            currentByte = pipe == 'F' ? f_input.read() : g_input.read();
        }
        System.out.println(pipe + " result: " + result);
        return result;
    }

    public void initialize() {
        try {
            int x = 1;
            Thread fThread = new Thread(new Worker(f_output, x, 'F'));
            Thread gThread = new Thread(new Worker(g_output, x, 'G'));

            fThread.start();
            gThread.start();

            StringBuilder fResult = getPipeResult('F');
            StringBuilder gResult = getPipeResult('G');

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    while (true) {
                        if (fResult.isEmpty() || gResult.isEmpty()) {
                            showModal();
                            Scanner scanner = new Scanner(System.in);
                            System.out.println(scanner.nextLine());
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, 5000);
            System.out.println("Result: " + (Integer.parseInt(fResult.toString()) + Integer.parseInt(gResult.toString())));
        } catch (IOException exception) {

        }
    }
}
