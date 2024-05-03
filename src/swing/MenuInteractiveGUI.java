package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInteractiveGUI extends JFrame {

    private JLabel resultLabel;

    public MenuInteractiveGUI() {
        super("Menú Interactivo - Problema de Parada de Turing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        String[] choices = {"CountDown", "CountUp"};
        JComboBox<String> classChoice = new JComboBox<>(choices);
        JButton btnHaltChecker = new JButton("Verificar con HaltChecker");
        JButton btnReverser = new JButton("Ejecutar con Reverser");
        resultLabel = new JLabel("Seleccione una clase y una operación.");

        btnHaltChecker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classChoice.getSelectedItem();
                if ("CountDown".equals(selectedClass)) {
                    boolean result = HaltChecker.willHalt(CountDown.class);
                    resultLabel.setText("CountDown se detiene: " + result);
                } else if ("CountUp".equals(selectedClass)) {
                    boolean result = HaltChecker.willHalt(CountUp.class);
                    resultLabel.setText("CountUp se detiene: " + result);
                }
            }
        });

        btnReverser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classChoice.getSelectedItem();
                if ("CountDown".equals(selectedClass)) {
                    runReverser(CountDown.class);
                } else if ("CountUp".equals(selectedClass)) {
                    runReverser(CountUp.class);
                }
            }
        });

        add(classChoice);
        add(btnHaltChecker);
        add(btnReverser);
        add(resultLabel);
    }

    private void runReverser(Class<?> programClass) {
        new Thread(() -> {
            boolean willHalt = HaltChecker.willHalt(programClass);
            if (willHalt) {
                SwingUtilities.invokeLater(() -> resultLabel.setText("Reverser: " + programClass.getSimpleName() + " entra en bucle infinito"));
            } else {
                SwingUtilities.invokeLater(() -> resultLabel.setText("Reverser: " + programClass.getSimpleName() + " termina inmediatamente porque no se detiene."));
            }
            Reverser.reverseBehavior(programClass);
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuInteractiveGUI frame = new MenuInteractiveGUI();
            frame.setVisible(true);
        });
    }
}
