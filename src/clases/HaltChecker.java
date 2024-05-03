package clases;

import java.util.concurrent.*;

public class HaltChecker {
    public static boolean willHalt(Class<?> programClass) {
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
            executor.shutdown();
            return true;  // Si el programa termina dentro de los 5 segundos, se detiene
        } catch (TimeoutException e) {
            System.out.println(programClass.getSimpleName() + " no se ha detenido en 5 segundos por lo que asumimos que es un bucle infinito");
            future.cancel(true);  // Intenta cancelar la ejecución
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error durante la ejecución: " + e.getMessage());
        } finally {
            executor.shutdownNow();  // Intenta detener todos los procesos activos
        }

        return false;  // Si no termina dentro del tiempo, se considera un bucle infinito
    }

    public static void main(String[] args) {
        System.out.println("¿Se detiene 'clases.CountDown'? " + willHalt(CountDown.class));
        System.out.println("¿Se detiene 'clases.CountUp'? " + willHalt(CountUp.class));
    }
}
