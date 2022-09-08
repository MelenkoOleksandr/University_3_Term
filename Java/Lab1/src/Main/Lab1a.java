package Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class ThreadWarrior extends  Thread {
    private JSlider slider;
    private JSpinner threadWarriorPower;
    private int increment = 0;
    private int count = 0, bound = 500000;

    public ThreadWarrior(JSlider slider, int increment, JSpinner threadWarriorPower) {
        this.slider = slider;
        this.increment = increment;
        this.threadWarriorPower = threadWarriorPower;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (count >= bound && ((slider.getValue() > 10 && increment < 0) || (slider.getValue() < 90 && increment > 0))){
                setPriority((int) threadWarriorPower.getValue());
                slider.setValue(slider.getValue() + this.increment);
                count = 0;
            }
            count++;
        }
    }
}


public class Lab1a {

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

        JButton button = new JButton("start");

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner warriorDecreasePower = new JSpinner(model);
        JSpinner warriorIncreasePower = new JSpinner(model2);

        panel.add(slider);
        panel.add(button);
        panel.add(warriorDecreasePower);
        panel.add(warriorIncreasePower);

        ThreadWarrior warriorDecrease = new ThreadWarrior(slider, -2, warriorDecreasePower);
        ThreadWarrior warriorIncrease = new ThreadWarrior(slider, 2, warriorIncreasePower);
        warriorDecreasePower.setValue(1);
        warriorIncreasePower.setValue(1);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (slider) {
                    warriorDecrease.start();
                    warriorIncrease.start();
                }
            }
        });



        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
