import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Description: Lottery Scheduling Algorithm
// Info: Every process has a ticket, the more tickets a process has, the more likely it is to be chosen
// In my implementation, total tickets are defined by the sum of all tickets of all processes
// Random number is generated between 1 and total tickets
// The process with the ticket that is in the range of the random number is chosen
public class LotteryScheduling {
    public final int MAX_WORKING_PERIOD = 2;
    ArrayList<Process> processes;
    Process currentProcess;
    int totalTickets;
    ArrayList<String> winnersHistory;

    public LotteryScheduling(ArrayList<Process> processes) {
        this.winnersHistory = new ArrayList<>();
        this.processes = processes;
        this.currentProcess = null;
        this.totalTickets = 0;
    }

    public void start() {
        int time = 0;

        while (true) {
            selectLotteryProcessWinner(time);
            updateProcesses();

            if (checkAllProcessesFinished()) {
                System.out.println("All processes finished");
                break;
            }
            time++;
        }
        printFinal();
    }

    public void selectLotteryProcessWinner(int time) {
        ArrayList<Process> listOfCurrentProcesses = getListOfCurrentProcesses(time);
        int currentTotalTickets = calculateCurrentTotalTickets(listOfCurrentProcesses);
        int selectedTicket = getRandomTicket(currentTotalTickets);
        currentProcess = getCurrentProcessByTicket(listOfCurrentProcesses, selectedTicket);
        if (currentProcess != null) {
            System.out.println("Selected ticket: " + selectedTicket + " of " + currentTotalTickets + "\nWinner: " + currentProcess.getProcessName());
            printChances(listOfCurrentProcesses, currentTotalTickets);
            winnersHistory.add(currentProcess.getProcessName());
        }
    }

    public void updateProcesses() {
        if (currentProcess != null) {
            for (int i = 1; i <= MAX_WORKING_PERIOD; i++) {
                currentProcess.decrementBurstTime();
                for (Process process : processes) {
                    if (currentProcess != process && !process.isFinished()) {
                        process.incrementWaitingTime();
                    }
                }

                if (currentProcess.isFinished()) {
                    System.out.println("Process: " + currentProcess.getProcessName() + " finished");
                    currentProcess = null;
                    break;
                }

            }
        }
    }
    public boolean checkAllProcessesFinished() {
        for (Process process : processes) {
            if (!process.isFinished()) {
                return false;
            }
        }

        return true;
    }


    public ArrayList<Process> getListOfCurrentProcesses(int time) {
        ArrayList<Process> listOfCurrentProcesses = new ArrayList<>();
        for (Process process : processes) {
            if (process.getArrivalTime() <= time && !process.isFinished()) {
                listOfCurrentProcesses.add(process);
            }
        }
        return listOfCurrentProcesses;
    }
    public int calculateCurrentTotalTickets(ArrayList<Process> listOfCurrentProcesses) {
        int currentTotalTickets = 0;
        for (Process process : listOfCurrentProcesses) {
            currentTotalTickets += process.getTickets();
        }
        return currentTotalTickets;
    }
    public int getRandomTicket(int currentTotalTickets) {
        return (int) (Math.random() * currentTotalTickets) + 1;
    }
    public Process getCurrentProcessByTicket(ArrayList<Process> listOfCurrentProcesses, int selectedTicket) {
        Process previousProcess = null;
        for (Process process : listOfCurrentProcesses) {
            if (previousProcess == null) {
                if (selectedTicket <= process.getTickets()) {
                    return process;
                }
            } else {
                if (selectedTicket > previousProcess.getTickets() && selectedTicket <= previousProcess.getTickets() + process.getTickets()) {
                    return process;
                }
            }
            previousProcess = process;
        }

        return null;
    }

    public void printProcessesTable() {
        String table = String.format("%-20s%-20s%-20s%-20s%-20s", "Process Name", "Arrival Time", "Burst Time", "Tickets", "Waiting Time");
        System.out.println(table);
        for (Process process : processes) {

                if (process.isFinished()) {
                    table = String.format("%-20s%-20s", process.getProcessName(), "Finished");
                } else {
                    table = String.format("%-20s%-20s%-20s%-20s%-20s", process.getProcessName(), process.getArrivalTime(), process.getBurstTime(), process.getTickets(), process.getWaitingTime());
                }

             System.out.println(table);
        }

    }

    public void printFinal() {
        String table = String.format("%-20s%-20s%-20s%-20s", "Process Name", "Working Time", "Tickets", "Waiting Time");
        System.out.println(table);
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            table = String.format("%-20s%-20s%-20s%-20s", process.getProcessName(), process.getWorkingTime(), process.getTickets(), process.getWaitingTime());
            System.out.println(table);
        }
        printWinnersHistory();
    }

    public void printWinnersHistory() {
        System.out.println("Winners History: ");
        for (String winner : winnersHistory) {
            System.out.print(winner + " ");
        }
    }

    public void printChances(ArrayList<Process> currentProcesses, int totalTickets) {
        System.out.print("Chances: ");
        for (Process process : currentProcesses) {
            System.out.printf("%s(%.2f)\t", process.getProcessName(), (double) process.getTickets() / totalTickets);
        }
        System.out.println();
    }
}
