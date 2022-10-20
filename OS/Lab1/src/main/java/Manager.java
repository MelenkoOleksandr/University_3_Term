import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Manager {
    Scanner scanner = new Scanner(System.in);
    PipedOutputStream f_output;
    PipedOutputStream g_output;
    PipedInputStream f_input;
    PipedInputStream g_input;
    int softFailsAmount = 0;
    int softFailsLimit = 5;
    boolean isResultCalculated = false;
    Integer result = 0;
    Thread fThread;
    Thread gThread;
    Thread gReader;
    Thread fReader;
    int argument = 0;
    boolean promptIsShown = true;
    boolean isFirstComputation = true;

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

    public void showCancellationModal() {
        System.out.println("(a) continue");
        System.out.println("(b) continue without prompt");
        System.out.println("(c) stop");
    }

    public synchronized void updateResult(int computationResult) {
        if (result != 0) {
            result += computationResult;
            System.out.println("Result of f(x) + g(x): " + result);
            isResultCalculated = true;

        } else {
            result += computationResult;
        }
    }

    public  void handleHardFail(char pipeName) {
        System.out.println("HARD FAIL ON " + pipeName + "!!!");
        System.exit(-1);
    }

    public  void handleSoftFail(char pipeName) throws IOException {
        System.out.println("SOFT FAIL ON " + pipeName + "!!!");

        softFailsAmount++;
        if (softFailsLimit <= softFailsAmount) {
            System.exit(-1);
        } else {
            result = 0;
            restartCalculations();

        }
    }

    public  void restartCalculations() {
        try {
            if (!isFirstComputation) {
                stopComputations();
            }
            f_output = new PipedOutputStream();
            g_output = new PipedOutputStream();
            f_input = new PipedInputStream(f_output);
            g_input = new PipedInputStream(g_output);
            fThread = new Worker(f_output, argument, 'F');
            gThread = new Worker(g_output, argument, 'G');
            gReader = new Reader('G', g_input, this);
            fReader = new Reader('F', f_input, this);
            fThread.start();
            gThread.start();
            fReader.start();
            gReader.start();
            fReader.join();
            gReader.join();
            fThread.join();
            gThread.join();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopComputations() {
        fReader.interrupt();
        gReader.interrupt();
        fThread.interrupt();
        gThread.interrupt();
    }
    public void handleTimeout(char pipeName) throws InterruptedException {
        if (promptIsShown) {
            System.out.println("Waiting time for computation of " + pipeName + "(x) exceeded time limit");
            showCancellationModal();
            String userChoice = scanner.nextLine();

            Thread.sleep(5000);

            if (!Objects.equals(userChoice, "")) {
                System.out.println("Override by system");
            }
            switch (userChoice) {
                case "a":
                    break;
                case "b":
                    promptIsShown = false;
                    break;
                case "c":
                    System.out.println("Finishing the program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Action is not confirmed within 5s, proceeding...");
            }
        }
    }

    public synchronized boolean isResultCalculated() {
        return isResultCalculated;
    }

    public void initialize() {

        System.out.println("If you want to start press ENTER");
        String userInput = scanner.nextLine();

        while (!Objects.equals(userInput, "stop")) {
            System.out.println("If you want to stop enter 'stop' or just write x for computations");
            userInput = scanner.nextLine();
            argument = Integer.parseInt((userInput));
            restartCalculations();
            isResultCalculated = false;
            result = 0;
            isFirstComputation = false;
        }
    }
}
