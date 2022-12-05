public class Process {
    private int arrivalTime;
    private int burstTime;
    private int workingTime;
    private int waitingTime;
    private int tickets = 1;
    private String processName;

    public Process(String processName, int arrivalTime, int burstTime, int tickets) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.tickets = tickets;
        this.waitingTime = 0;
        this.workingTime = 0;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void incrementWaitingTime() {
        this.waitingTime++;
    }

    public void decrementBurstTime() {
        this.burstTime--;
        this.workingTime++;
    }

    public boolean isFinished() {
        return this.burstTime == 0;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getWorkingTime() {
        return workingTime;
    }
}
