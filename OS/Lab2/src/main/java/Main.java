import java.util.ArrayList;

public class Main {
    public static ArrayList<Process> initProcesses() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("A", 0, 20, 400));
        processes.add(new Process("B", 1, 20, 600));
        processes.add(new Process("C", 0, 4, 1));
        processes.add(new Process("D", 4, 5, 50));
        processes.add(new Process("E", 8, 2, 35));
        return processes;
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = initProcesses();
        LotteryScheduling lotteryScheduling = new LotteryScheduling(processes);
        lotteryScheduling.start();
    }
}
