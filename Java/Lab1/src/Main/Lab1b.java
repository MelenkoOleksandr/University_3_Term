package Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicReference;


public class Lab1b {
    public static boolean semaphore = false;
    private static Object sync = new Object();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        JSlider slider = new JSlider();
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JButton buttonStart1 = new JButton("Start1");
        JButton buttonStart2 = new JButton("Start2");
        JButton buttonStop1 = new JButton("Stop1");
        JButton buttonStop2 = new JButton("Stop2");
        JLabel label = new JLabel("Вільно");

        panel.add(slider);
        panel.add(buttonStart1);
        panel.add(buttonStart2);
        panel.add(buttonStop1);
        panel.add(buttonStop2);
        panel.add(label);

        AtomicReference<ThreadSenaphore> thread1 = new AtomicReference<>(new ThreadSenaphore(slider, 1, 90));
        AtomicReference<ThreadSenaphore> thread2 = new AtomicReference<>(new ThreadSenaphore(slider, -1, 10));

        buttonStart1.addActionListener((ActionEvent e) -> {
            if (!semaphore) {
                thread1.set(new ThreadSenaphore(slider, 1, 90));
                thread1.get().setPriority(Thread.MIN_PRIORITY);
                thread1.get().start();
                semaphore = true;
                label.setText("Зайнято");
            }
        });

        buttonStart2.addActionListener((ActionEvent e) -> {
            if (!semaphore) {
                thread2.set(new ThreadSenaphore(slider, -1, 10));
                thread2.get().setPriority(Thread.MAX_PRIORITY);
                thread2.get().start();
                semaphore = true;
                label.setText("Зайнято");
            };
        });

        buttonStop1.addActionListener((ActionEvent e) -> {
            thread1.get().interrupt();
            semaphore = false;
            label.setText("Вільно");
        });

        buttonStop2.addActionListener((ActionEvent e) -> {
            thread2.get().interrupt();
            semaphore = false;
            label.setText("Вільно");
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

class ThreadSenaphore extends Thread {
    JSlider slider;
    private int increment = 0;
    private int limit = 0;

    public ThreadSenaphore(JSlider slider, int increment, int limit) {
        this.slider = slider;
        this.increment = increment;
        this.limit = limit;
    }

    @Override
    public void run() {
        synchronized (slider) {
            while (!interrupted()) {
                int currentValue =  slider.getValue();
                if (increment < 0 && limit < currentValue) {
                    slider.setValue(currentValue + increment);
                } else if (increment > 0 && limit > currentValue) {
                    slider.setValue(currentValue + increment);
                }
            }
        }
    }
}

