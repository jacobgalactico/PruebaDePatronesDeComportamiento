package clases.strategy;

import clases.strategy.HaltStrategy;

import java.util.concurrent.*;

public class TimeBasedHaltStrategy implements HaltStrategy {
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
