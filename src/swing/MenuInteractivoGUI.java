import javax.swing.*;
import java.awt.*;

import clases.HaltChecker;
import clases.strategy.TimeHaltStrategy;
import clases.CountDown;
import clases.CountUp;
import clases.Reverser;

/**
 * Clase {@code MenuInteractiveGUI} que crea una interfaz gráfica para interactuar con las funcionalidades
 * de verificación y reversión basadas en el problema de parada de Turing.
 * La interfaz permite seleccionar entre distintos programas para evaluar si se detendrán o no,
 * utilizando un {@code HaltChecker} con una estrategia de tiempo definida.
 * <p>
 * La interfaz incluye un menú desplegable para seleccionar el programa a verificar, botones para iniciar
 * la verificación o ejecutar el comportamiento inverso del programa seleccionado, y botones para
 * interrumpir y reiniciar la operación en curso. También se incluyen etiquetas para mostrar los resultados
 * de las operaciones y el estado actual de la aplicación.
 * </p>
 *
 * @author Jacob Altenburger
 */

public class MenuInteractivoGUI extends JFrame {
    //Etiqueta para mostrar el resultado de las operaciones.
    private JLabel resultLabel;
    //Etiqueta para mostrar el estado actual de la aplicación.
    private JLabel statusLabel;
    //Barra de progreso para indicar la ejecución de tareas en segundo plano.
    private JProgressBar progressBar;
    //Instancia de HaltChecker para verificar si un programa se detiene.
    private HaltChecker haltChecker;
    //Menú desplegable para seleccionar el programa a verificar.
    private JComboBox<String> classChoice;
    //Hilo para ejecutar operaciones que podrían bloquearse, como la verificación de parada.
    private Thread runningThread;


    /**
     * Constructor que inicializa la interfaz gráfica, configurando componentes y eventos.
     */
    public MenuInteractivoGUI() {
        //Configuracion inicial del JFrame y el diseño de la interfaz.
        super("Menú Interactivo - Problema de Parada de Turing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        haltChecker = new HaltChecker(new TimeHaltStrategy());

        String[] choices = {"CountDown", "CountUp"};
        classChoice = new JComboBox<>(choices);
        JButton btnHaltChecker = new JButton("Verificar con HaltChecker");
        JButton btnReverser = new JButton("Ejecutar con Reverser");
        JButton btnReset = new JButton("Interrumpir y Reiniciar");
        resultLabel = new JLabel("Seleccione una clase y una operación.");
        statusLabel = new JLabel();

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false); // Inicialmente invisible

        btnHaltChecker.addActionListener(e -> {
            statusLabel.setText("Verificando...");
            progressBar.setVisible(true); // Muestra la barra de progreso
            new Timer(500, event -> {
                SwingUtilities.invokeLater(() -> {
                    try {
                        String selectedClass = (String) classChoice.getSelectedItem();
                        boolean result = false;
                        if ("CountDown".equals(selectedClass)) {
                            result = haltChecker.checkHalt(CountDown.class);
                        } else if ("CountUp".equals(selectedClass)) {
                            result = haltChecker.checkHalt(CountUp.class);
                        }
                        resultLabel.setText(selectedClass + " se detiene: " + result);
                        statusLabel.setText("Verificación completada.");
                    } finally {
                        progressBar.setVisible(false); // Oculta la barra de progreso después de un breve retardo
                    }
                });
                ((Timer) event.getSource()).stop(); // Detiene el temporizador después de ejecutar una vez
            }).start();
        });

        btnReverser.addActionListener(e -> {
            statusLabel.setText("Ejecutando Reverser...");
            progressBar.setVisible(true); // Muestra la barra de progreso
            runReverser(classChoice.getSelectedItem().toString());
        });

        btnReset.addActionListener(e -> {
            if (runningThread != null && runningThread.isAlive()) {
                runningThread.interrupt();
            }
            resetApplication();
        });

        add(classChoice);
        add(btnHaltChecker);
        add(btnReverser);
        add(btnReset);
        add(resultLabel);
        add(statusLabel);
        add(progressBar);
    }
    /**
     * Método para ejecutar la lógica de {@code Reverser} en un hilo separado.
     * Determina el comportamiento basado en si el programa seleccionado se detendrá.
     *
     * @param className El nombre de la clase del programa a verificar y ejecutar con {@code Reverser}.
     */
    private void runReverser(String className) {
        runningThread = new Thread(() -> {
            try {
                Class<?> programClass = "CountDown".equals(className) ? CountDown.class : CountUp.class;
                boolean willHalt = haltChecker.checkHalt(programClass);
                SwingUtilities.invokeLater(() -> {
                    if (willHalt) {
                        resultLabel.setText("Reverser: " + programClass.getSimpleName() + " entra en bucle infinito");
                    } else {
                        resultLabel.setText("Reverser: " + programClass.getSimpleName() + " no termina según HaltChecker, por lo tanto Reverser lo detiene.");
                    }
                    statusLabel.setText("Reverser completado.");
                });
                Reverser.reverseBehavior(programClass, haltChecker);
            } finally {
                progressBar.setVisible(false); // Oculta la barra de progreso al terminar la ejecución de Reverser
            }
        });
        runningThread.start();
    }
    /**
     * Método para reiniciar la aplicación, interrumpiendo cualquier operación en curso
     * y reestableciendo los componentes de la interfaz a su estado inicial.
     */
    private void resetApplication() {
        if (runningThread != null && runningThread.isAlive()) {
            runningThread.interrupt();
        }
        progressBar.setVisible(false);
        resultLabel.setText("Seleccione una clase y una operación.");
        statusLabel.setText("");
        classChoice.setSelectedIndex(0);
    }
    /**
     * Punto de entrada principal de la aplicación que lanza la interfaz gráfica.
     *
     * @param args Argumentos de la línea de comando (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuInteractivoGUI frame = new MenuInteractivoGUI();
            frame.setVisible(true);
        });
    }
}
