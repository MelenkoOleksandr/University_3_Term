package Taskb;

public class Monitor {
    private boolean isSwappersEqual = false;
    private final int[] swapperAmountTable = new int[4];

    public synchronized boolean isSwappersEqual() {
        return isSwappersEqual;
    }

    public synchronized  void setSwapperAmount(int swapperIndex, int swapperAmount) {
        swapperAmountTable[swapperIndex] = swapperAmount;
    }

    public synchronized void checkEquality() {
        // 1,2,3 || 1,3,4 || 2,3,4 || 1,2,4
        isSwappersEqual=  swapperAmountTable[0] == swapperAmountTable[1] && swapperAmountTable[0] == swapperAmountTable[2] ||
                swapperAmountTable[0] == swapperAmountTable[1] && swapperAmountTable[0] == swapperAmountTable[3] ||
                swapperAmountTable[0] == swapperAmountTable[2] && swapperAmountTable[0] == swapperAmountTable[3] ||
                swapperAmountTable[1] == swapperAmountTable[2] && swapperAmountTable[1] == swapperAmountTable[3];
    }
}
