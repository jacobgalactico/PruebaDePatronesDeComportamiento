package clases.strategy;

/**
 * La interfaz {@code HaltStrategy} define un contrato para las estrategias de detención
 * que determinan si un determinado programa representado por una clase Java debe detenerse.
 * <p>
 * Esta interfaz es utilizada para implementar el patrón de diseño estrategia, permitiendo
 * la inyección de diferentes comportamientos de detención en tiempo de ejecución.
 * Las clases que implementan esta interfaz deben definir el método {@code willHalt},
 * que evalúa si una clase de programa específica debe detener su ejecución.
 * </p>
 *
 * @author Jacob Altenburger
 */
public interface HaltStrategy {

    /**
     * Determina si el programa especificado por la clase dada debe detener su ejecución.
     *
     * @param programClass la clase del programa a evaluar. No debe ser {@code null}.
     * @return {@code true} si el programa debe detenerse según la estrategia implementada,
     *         {@code false} en caso contrario.
     */
    boolean willHalt(Class<?> programClass);
}
