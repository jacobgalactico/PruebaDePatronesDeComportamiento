package clases;

/**
 * La clase {@code CountUp} implementa un contador ascendente que comienza en 1 y
 * aumenta indefinidamente cada segundo. Este contador se imprime en la consola.
 * <p>
 * La ejecución del contador continuará hasta que se interrumpa el hilo en el que se ejecuta,
 * por ejemplo, mediante una interrupción externa o un cierre de la aplicación.
 * </p>
 *
 * @author Jacob Altenburger
 */
public class CountUp {

    /**
     * Ejecuta un bucle infinito que incrementa y muestra un contador cada segundo.
     * El contador se incrementa de uno en uno, comenzando desde 1. Si el hilo en el que
     * se ejecuta el método es interrumpido, el método captura la excepción {@code InterruptedException},
     * interrumpe el hilo y termina la ejecución.
     *
     * @param args argumentos de la línea de comando (no utilizados en este programa)
     */
    public static void main(String[] args) {
        int num = 1;

        while (true) {
            System.out.println(num);
            num++;
            try {
                Thread.sleep(1000);  // Duerme 1 segundo en cada iteración
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
