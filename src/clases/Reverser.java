package clases;

import clases.strategy.TimeHaltStrategy;

import java.util.Scanner;

/**
 * La clase {@code Reverser} implementa una lógica de comportamiento inverso basada en la determinación
 * de si un programa específico se detendrá o no.
 * <p>
 * Esta clase utiliza {@code HaltChecker} para evaluar si un programa dado se detendrá. Dependiendo del
 * resultado, {@code Reverser} puede entrar en un bucle infinito si el programa se detiene, o terminar
 * inmediatamente si el programa no se detiene.
 * </p>
 *
 * @author Jacob Altenburger
 */
public class Reverser {

    /**
     * Ejecuta la lógica inversa basada en la detención o no de la clase del programa especificado.
     * Si el programa se detiene, este método entra en un bucle infinito. Si no, termina inmediatamente.
     *
     * @param programClass la clase del programa a evaluar
     * @param checker el {@code HaltChecker} utilizado para determinar si el programa se detiene
     */
    public static void reverseBehavior(Class<?> programClass, HaltChecker checker) {
        boolean willHalt = checker.checkHalt(programClass);

        if (willHalt) {
            System.out.println("Entrando en un bucle infinito porque " + programClass.getSimpleName() + " se detiene.");
            while (true) {
                System.out.println("Reverser está en un bucle infinito porque " + programClass.getSimpleName() + " se detiene.");
                try {
                    Thread.sleep(1000);  // He añadido un sleep para evitar consumo excesivo de CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupción del bucle infinito.");
                    break;
                }
            }
        } else {
            System.out.println(programClass.getSimpleName() + " termina inmediatamente porque no se detiene.");
        }
    }

    /**
     * Punto de entrada principal del programa que pide al usuario que elija una clase para evaluar.
     * Basándose en la entrada del usuario, se aplica la lógica de comportamiento inverso.
     *
     * @param args argumentos de la línea de comando (no utilizados en este programa)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué entrada quieres elegir? (Escribe 'clases.CountDown' o 'clases.CountUp')");
        String input = scanner.nextLine();
        HaltChecker checker = new HaltChecker(new TimeHaltStrategy()); // Crea una instancia de HaltChecker con una estrategia

        if ("clases.CountDown".equalsIgnoreCase(input)) {
            reverseBehavior(CountDown.class, checker);
        } else if ("clases.CountUp".equalsIgnoreCase(input)) {
            reverseBehavior(CountUp.class, checker);
        } else {
            System.out.println("Entrada no reconocida. Por favor, escribe 'clases.CountDown' o 'clases.CountUp'.");
        }

        scanner.close();
    }
}
