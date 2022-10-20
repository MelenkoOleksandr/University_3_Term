

import os.lab1.compfuncs.basic.IntOps;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.Optional;

//class IntOps {
//    public static Optional<Integer> trialF(Integer x){
//        return Optional.of(x - 2);
//    }
//
//    public static Optional<Integer> trialG(Integer x){
//        return Optional.of(x * (x - 1));
//    }
//}

public class Worker implements Runnable {
    int argument;
    PipedOutputStream writerPipe;
    char funcName;
    Optional<Integer> result;

    public Worker(PipedOutputStream writerPipe, int argument, char funcName) {
        this.argument = argument;
        this.writerPipe = writerPipe;
        this.funcName = funcName;
    }

    @Override
    public void run() {
        try {
            result = funcName == 'F' ? IntOps.trialF(argument) : IntOps.trialG(argument);
            if (result.isPresent()) {
//                System.out.println(funcName == 'F' ? "F Result: " + result.get() : "G Result: " + result.get());
                writerPipe.write(result.get().toString().getBytes());
                writerPipe.close();
            } else {
                writerPipe.write("-1".getBytes());
            }
//            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }
}
