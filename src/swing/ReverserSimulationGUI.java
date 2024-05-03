package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import clases.CountDown;
import clases.CountUp;
import clases.Reverser;  // Asegúrate de tener esta importación

public class ReverserSimulationGUI extends JFrame {
    private JButton countDownButton;
    private JButton countUpButton;
    private JTextArea resultArea;

    public ReverserSimulationGUI() {
        super("Reverser Simulation");
        createUI();
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void createUI() {
        JLabel welcomeLabel = new JLabel("Bienvenido a la simulación del Reverser", SwingConstants.CENTER);
        JLabel instructionLabel = new JLabel("Elige el programa para analizar con Reverser:", SwingConstants.CENTER);

        countDownButton = new JButton("Count Down");
        countUpButton = new JButton("Count Up");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); // Hace que el área de texto sea desplazable

        countDownButton.addActionListener(this::handleCountDownAction);
        countUpButton.addActionListener(this::handleCountUpAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(countDownButton);
        buttonPanel.add(countUpButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void handleCountDownAction(ActionEvent event) {
        resultArea.setText("Ejecutando Reverser para CountDown...");
        // Asumiendo que Reverser tiene un método apropiado para manejar esto
        new Thread(() -> Reverser.reverseBehavior(CountDown.class)).start();
    }

    private void handleCountUpAction(ActionEvent event) {
        resultArea.setText("Ejecutando Reverser para CountUp...");
        // Asumiendo que Reverser tiene un método apropiado para manejar esto
        new Thread(() -> Reverser.reverseBehavior(CountUp.class)).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReverserSimulationGUI::new);
    }
}
