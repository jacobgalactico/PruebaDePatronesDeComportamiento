package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import clases.CountDown;
import clases.CountUp;
import clases.HaltChecker;

public class HaltSimulationGUI extends JFrame {
    private JButton countDownButton;
    private JButton countUpButton;
    private JTextArea resultArea;

    public HaltSimulationGUI() {
        super("Halt Problem Simulation");
        createUI();
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUI() {
        countDownButton = new JButton("Count Down");
        countUpButton = new JButton("Count Up");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        countDownButton.addActionListener(this::handleCountDownAction);
        countUpButton.addActionListener(this::handleCountUpAction);

        JPanel panel = new JPanel();
        panel.add(countDownButton);
        panel.add(countUpButton);

        getContentPane().add(BorderLayout.CENTER, resultArea);
        getContentPane().add(BorderLayout.SOUTH, panel);
    }

    private void handleCountDownAction(ActionEvent event) {
        runSimulation(CountDown.class);
    }

    private void handleCountUpAction(ActionEvent event) {
        runSimulation(CountUp.class);
    }

    private void runSimulation(Class<?> programClass) {
        boolean halts = HaltChecker.willHalt(programClass);
        resultArea.setText(programClass.getSimpleName() + " " + (halts ? "halts." : "does not halt."));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HaltSimulationGUI::new);
    }
}
