package clases;

import java.util.Scanner;

public class Reverser {

    public static void reverseBehavior(Class<?> programClass) {
        // Utiliza clases.HaltChecker para determinar si el programa original se detiene o no
        boolean willHalt = HaltChecker.willHalt(programClass);

        if (willHalt) {
            // Si el programa original se detiene, entrar en un bucle infinito
            System.out.println("Entrando en un bucle infinito porque " + programClass.getSimpleName() + " se detiene.");
            while (true) {
                System.out.println("Reverser está en un bucle infinito porque CountDown se detiene.");
                try {
                    Thread.sleep(1000);  // He añadido un sleep para evitar consumo excesivo de CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupción del bucle infinito.");
                }
            }
        } else {
            // Si el programa original no se detiene, entonces terminar inmediatamente
            System.out.println("clases.Reverser termina inmediatamente porque " + programClass.getSimpleName() + " no se detiene.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Qué entrada quieres elegir? (Escribe 'clases.CountDown' o 'clases.CountUp')");
        String input = scanner.nextLine();

        if ("clases.CountDown".equalsIgnoreCase(input)) {
            reverseBehavior(CountDown.class);
        } else if ("clases.CountUp".equalsIgnoreCase(input)) {
            reverseBehavior(CountUp.class);
        } else {
            System.out.println("Entrada no reconocida. Por favor, escribe 'clases.CountDown' o 'clases.CountUp'.");
        }

        scanner.close();
    }
}
