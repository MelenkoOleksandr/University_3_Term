import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Manager {
    final PipedOutputStream f_output;
    final PipedOutputStream g_output;
    final PipedInputStream f_input;
    final PipedInputStream g_input;

    public Manager() throws IOException {
        f_output = new PipedOutputStream();
        g_output = new PipedOutputStream();
        f_input = new PipedInputStream(f_output);
        g_input = new PipedInputStream(g_output);
    }

    public void initialize() {
        Thread threadFListener = new Thread(() -> {
            try {
                while (true) {
                    f_output.write((int) (Math.random() * 10));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadGListener = new Thread(() -> {
            try {
                while (true) {
                    g_output.write((int) (Math.random() * 10));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadFWriter = new Thread(() -> {
            try {
                int data = f_input.read();
                while (data != -1) {
                    System.out.println("F  Writer: " + data);
                    data = f_input.read();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Thread threadGWriter = new Thread(() -> {
            try {
                int data = g_input.read();
                while (data != -1) {
                    System.out.println("G  Writer: " + data);
                    data = g_input.read();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        threadFListener.start();
        threadFWriter.start();
        threadGListener.start();
        threadGWriter.start();
    }
}
