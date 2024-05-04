package clases.strategy;

import java.util.concurrent.*;

/**
 * La clase {@code TimeHaltStrategy} implementa {@code HaltStrategy} utilizando una estrategia basada en el tiempo.
 * Determina si un programa debe detenerse al intentar ejecutar el método principal de la clase del programa
 * en un hilo separado y comprobar si se completa en un tiempo predefinido.
 * <p>
 * Esta estrategia supone que si el programa no se completa en 5 segundos, entonces está diseñado para continuar
 * ejecutándose indefinidamente. (Recordatorio de que es una simulación del problema de parar).
 * </p>
 *
 * @author Jacob Altenburger
 */
public class TimeHaltStrategy implements HaltStrategy {

    /**
     * Evalúa si la clase del programa dado debe detener su ejecución basándose en un límite de tiempo.
     * Si el método {@code main} de la clase no finaliza dentro de 5 segundos, se considera que el programa
     * no debe detenerse. Si se produce cualquier excepción o el tiempo se excede, se asume que el programa
     * podría continuar ejecutándose.
     *
     * @param programClass la clase del programa a evaluar, que debe tener un método {@code main} accesible.
     * @return {@code true} si el programa se completa dentro de 5 segundos, {@code false} si excede este tiempo
     *         o si ocurre un error durante su ejecución.
     */
    @Override
    public boolean willHalt(Class<?> programClass) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                programClass.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
            } catch (Exception e) {
                System.out.println("Error al ejecutar el programa: " + e.getMessage());
            }
        });

        try {
            future.get(5, TimeUnit.SECONDS);  // Espera hasta 5 segundos
            return true;
        } catch (TimeoutException e) {
            System.out.println(programClass.getSimpleName() + " no se ha detenido en 5 segundos");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error durante la ejecución: " + e.getMessage());
        } finally {
            executor.shutdownNow();
        }

        return false;
    }
}
