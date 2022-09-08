package Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Lab1b {
    public static boolean semaphore = false;

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

        ThreadSenaphore thread1 = new ThreadSenaphore(slider, 1, 90);
        ThreadSenaphore thread2 = new ThreadSenaphore(slider, -1, 10);
        thread1.start();
        thread2.start();
        buttonStart1.addActionListener((ActionEvent e) -> {
            if (!semaphore) {
                semaphore = true;
                thread1.setPriority(Thread.MIN_PRIORITY);
                thread1.setIsWorking(semaphore);
                label.setText("Зайнято");
            }
        });

        buttonStart2.addActionListener((ActionEvent e) -> {
            if (!semaphore) {
                semaphore = true;
                thread2.setPriority(Thread.MAX_PRIORITY);
                thread2.setIsWorking(semaphore);
                label.setText("Зайнято");
            };
        });

        buttonStop1.addActionListener((ActionEvent e) -> {
            semaphore = false;
            thread1.setIsWorking(semaphore);
            label.setText("Вільно");
        });

        buttonStop2.addActionListener((ActionEvent e) -> {
            semaphore = false;
            thread2.setIsWorking(semaphore);
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
    private boolean isWorking = false;

    public ThreadSenaphore(JSlider slider, int increment, int limit) {
        this.slider = slider;
        this.increment = increment;
        this.limit = limit;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (isWorking) {
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

