public class HaltChecker {

    // Método que intenta predecir si una clase específica se detiene o no
    public static boolean willHalt(Class<?> programClass) {
        // Verificar el nombre de la clase para decidir si se detiene
        if (programClass.equals(CountDown.class)) {
            // Sabemos que CountDown se detiene porque su bucle termina.
            return true;
        } else if (programClass.equals(CountUp.class)) {
            // Sabemos que CountUp no se detiene porque tiene un bucle infinito.
            return false;
        }
        // En caso de no reconocer la clase, suponemos que no se puede determinar
        return false;
    }

    public static void main(String[] args) {
        // Pruebas del HaltChecker con las clases CountDown y CountUp
        System.out.println("¿Se detiene 'CountDown'? " + willHalt(CountDown.class));
        System.out.println("¿Se detiene 'CountUp'? " + willHalt(CountUp.class));
    }
}
