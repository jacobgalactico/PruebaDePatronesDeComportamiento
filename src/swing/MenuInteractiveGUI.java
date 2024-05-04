package clases;

import clases.strategy.TimeBasedHaltStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInteractiveGUI extends JFrame {

    private JLabel resultLabel;
    private HaltChecker haltChecker;

    public MenuInteractiveGUI() {
        super("Menú Interactivo - Problema de Parada de Turing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        haltChecker = new HaltChecker(new TimeBasedHaltStrategy()); // Inicializa HaltChecker con la estrategia deseada

        String[] choices = {"CountDown", "CountUp"};
        JComboBox<String> classChoice = new JComboBox<>(choices);
        JButton btnHaltChecker = new JButton("Verificar con HaltChecker");
        JButton btnReverser = new JButton("Ejecutar con Reverser");
        resultLabel = new JLabel("Seleccione una clase y una operación.");

        btnHaltChecker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classChoice.getSelectedItem();
                boolean result = false;
                if ("CountDown".equals(selectedClass)) {
                    result = haltChecker.checkHalt(CountDown.class);
                } else if ("CountUp".equals(selectedClass)) {
                    result = haltChecker.checkHalt(CountUp.class);
                }
                resultLabel.setText(selectedClass + " se detiene: " + result);
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
            // Asegúrate de pasar ambos, la clase y la instancia de HaltChecker
            boolean willHalt = haltChecker.checkHalt(programClass);
            if (willHalt) {
                SwingUtilities.invokeLater(() -> resultLabel.setText("Reverser: " + programClass.getSimpleName() + " entra en bucle infinito"));
            } else {
                SwingUtilities.invokeLater(() -> resultLabel.setText("Reverser: " + programClass.getSimpleName() + " no termina según HaltChecker, por lo tanto Reverser lo detiene."));
            }
            Reverser.reverseBehavior(programClass, haltChecker); // Pasar la instancia de HaltChecker
        }).start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuInteractiveGUI frame = new MenuInteractiveGUI();
            frame.setVisible(true);
        });
    }
}
