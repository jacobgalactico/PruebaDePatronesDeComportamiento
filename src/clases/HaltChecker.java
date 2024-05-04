package clases;

import clases.strategy.HaltStrategy;

/**
 * La clase {@code HaltChecker} proporciona una manera de verificar si un programa
 * específico, representado por una clase, debería detenerse o no según una lógica definida.
 * Utiliza una lógica de detención para evaluar si la ejecución de un programa debe ser
 * interrumpida.(si dura más de 5 segundos se detiene porque da por hecho que es un bucle infinito)
 * <p>
 * Esta clase es parte de una implementación del patrón de diseño strategy, donde {@code HaltStrategy}
 * define el método {@code willHalt}, que {@code HaltChecker} utiliza para determinar el comportamiento
 * de detención.
 * </p>
 *
 * @author Jacob Altenburger
 */
public class HaltChecker {
    private HaltStrategy strategy;

    /**
     * Construye un {@code HaltChecker} con una estrategia específica.
     *
     * @param strategy la estrategia de detención a utilizar para decidir si se detiene
     *                 la ejecución de un programa. No debe ser {@code null}.
     */
    public HaltChecker(HaltStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Evalúa si la clase del programa especificado debe ser detenida según la
     * estrategia de detención configurada.
     *
     * @param programClass la clase del programa a evaluar. No debe ser {@code null}.
     * @return {@code true} si la estrategia determina que la clase del programa debe
     *         detenerse, {@code false} en caso contrario.
     */
    public boolean checkHalt(Class<?> programClass) {
        return strategy.willHalt(programClass);
    }
}
