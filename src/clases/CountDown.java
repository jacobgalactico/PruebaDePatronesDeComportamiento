package clases;

/**
 * La clase {@code CountDown} implementa una cuenta regresiva desde 10 hasta 1,
 * imprimiendo cada número en la consola. Al finalizar la cuenta, imprime un mensaje
 * indicando que el programa ha terminado.
 * <p>
 * Este programa demuestra el uso de un bucle while para realizar la cuenta regresiva.
 * </p>
 * @author Jacob Altenburger
 */
public class CountDown {

    /**
     * El método {@code main} ejecuta la cuenta regresiva. Inicia en 10 y decrementa
     * el valor de {@code num} en cada iteración del bucle, imprimiendo el valor actual
     * de {@code num}. Cuando {@code num} es 0, el bucle termina y se imprime un mensaje
     * final.
     *
     * @param args argumentos de línea de comando (no utilizados en este programa)
     */
    public static void main(String[] args) {
        int num = 10;

        while (num > 0) {
            System.out.println(num);
            num--; // Restamos 1.
        }

        System.out.println("El programa ha terminado."); // El programa se detiene cuando num llega a 0.
    }
}
