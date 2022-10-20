import java.io.IOException;
import java.io.PipedInputStream;

public class Reader extends Thread {
    final char pipe;
    PipedInputStream input;
    Manager manager;
    StringBuilder computedResult;

    public Reader(char pipe, PipedInputStream input, Manager manager) {
        this.pipe = pipe;
        this.input = input;
        this.manager = manager;
    }

    public StringBuilder getPipeResult() throws IOException {
        StringBuilder result = new StringBuilder();
        int currentByte = input.read();

        while (currentByte != -1) {
            result.append((char)currentByte);
            currentByte = input.read();
        }
        return result;
    }

    @Override
    public void run() {
        while (!manager.isResultCalculated() && !Thread.interrupted()) {
            try {
                computedResult = getPipeResult();
                switch (computedResult.toString()) {
                    case "Hard Fail":
                        manager.handleHardFail(pipe);
                        break;
                    case "Soft Fail":
                        manager.handleSoftFail(pipe);
                        break;
                    case "Timeout":
                        manager.handleTimeout(pipe);
                        break;
                    default:
                        if (!computedResult.isEmpty()) {
                            System.out.println(pipe + " result: " + (Integer.parseInt(computedResult.toString())));
                            manager.updateResult(Integer.parseInt(computedResult.toString()));
                        }
                        break;
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
