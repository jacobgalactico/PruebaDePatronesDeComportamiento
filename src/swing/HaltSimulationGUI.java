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
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void createUI() {
        // Etiquetas de bienvenida e instrucciones
        JLabel welcomeLabel = new JLabel("Bienvenido a la simulación del problema de parar", SwingConstants.CENTER);
        JLabel instructionLabel = new JLabel("Elige el código que deseas analizar:", SwingConstants.CENTER);

        // Configuración de botones
        countDownButton = new JButton("Count Down");
        countUpButton = new JButton("Count Up");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); // Para que el área de texto sea scrollable

        // Listeners de los botones
        countDownButton.addActionListener(this::handleCountDownAction);
        countUpButton.addActionListener(this::handleCountUpAction);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(countDownButton);
        buttonPanel.add(countUpButton);

        // Panel principal que contiene todo
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH); // Área de texto en la parte más baja del panel

        // Añadir componentes al frame
        getContentPane().setLayout(new BorderLayout()); // Usando BorderLayout en el frame
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);  // Texto de bienvenida en la parte superior
        getContentPane().add(mainPanel, BorderLayout.CENTER);  // Panel principal en el centro

        // Ajustes de estilo de las etiquetas
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
