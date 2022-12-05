package Taskb;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Swapper extends Thread {
    Random random = new Random();
    CyclicBarrier cyclicBarrier;
    Monitor monitor;
    String line;
    int swapperIndex;

    public Swapper(CyclicBarrier cyclicBarrier, Monitor monitor, String line, int swapperIndex) {
        this.cyclicBarrier = cyclicBarrier;
        this.monitor = monitor;
        this.line = line;
        this.swapperIndex = swapperIndex;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            while (!monitor.isSwappersEqual()) {
                int charIndex = random.nextInt(line.length());
                switch (line.charAt(charIndex)) {
                    case 'A' -> line = line.substring(0, charIndex) + "C" + line.substring(charIndex + 1);
                    case 'B' -> line = line.substring(0, charIndex) + "D" + line.substring(charIndex + 1);
                    case 'C' -> line = line.substring(0, charIndex) + "A" + line.substring(charIndex + 1);
                    case 'D' -> line = line.substring(0, charIndex) + "B" + line.substring(charIndex + 1);
                }
                monitor.setSwapperAmount(swapperIndex, getAmount());
                System.out.println("Swapper" + swapperIndex + "'s line before barrier: " + line + " amount: " + getAmount());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("-------------BARRIERRR-------------");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                monitor.checkEquality();
            }
        }

    }

    public int getAmount() {
        int amount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'A' || line.charAt(i) == 'B') {
                amount++;
            }
        }
        return amount;
    }
}
