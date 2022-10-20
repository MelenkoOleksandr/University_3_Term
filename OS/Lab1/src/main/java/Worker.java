

import os.lab1.compfuncs.advanced.IntOps;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.Optional;
import java.util.concurrent.*;


public class Worker extends Thread {
    int argument;
    PipedOutputStream writerPipe;
    char funcName;


    public Worker(PipedOutputStream writerPipe, int argument, char funcName) {
        this.argument = argument;
        this.writerPipe = writerPipe;
        this.funcName = funcName;
    }

    @Override
    public void run()  {

            try {
                ExecutorService executorCompletionService = Executors.newSingleThreadExecutor();
                Future<Optional<Optional<Integer>>> future = executorCompletionService.submit(() -> funcName == 'F' ? IntOps.trialF(argument) : IntOps.trialG(argument));
                Optional<Optional<Integer>> result;
                try {
                    result = future.get(10000, TimeUnit.MILLISECONDS);
                    if (result.isPresent()) {
                        if (result.get().isPresent()) {
                            // Computed Values
                            writerPipe.write(result.get().get().toString().getBytes());
                            writerPipe.close();
                        } else {
                            // Hard Fail
                            writerPipe.write("Hard Fail".getBytes());
                            writerPipe.close();
                        }
                    } else {
                        // Soft Fail
                        writerPipe.write("Soft Fail".getBytes());
                        writerPipe.close();
                    }
                } catch (ExecutionException | TimeoutException e) {
                    writerPipe.write("Timeout".getBytes());
                    writerPipe.close();
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("ERROR");
                throw new RuntimeException(e);
            }


    }
}
