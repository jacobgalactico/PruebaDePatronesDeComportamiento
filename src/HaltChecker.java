public class HaltChecker {

    // Método que intenta predecir si una clase se detiene o no
    public static boolean willHalt(Class<?> programClass) {
        // Verificar el nombre de la clase para decidir si se detiene
        if (programClass.equals(CountDown.class)) {
            //Sabemos que CountDown se detiene porque su bucle termina.
            return true;
        } else if (programClass.equals(CountUp.class)) {
            //Sabemos que CountUp no se detiene porque tiene un bucle infinito(da igual el valor de entrada).
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("¿Se detiene 'CountDown'? " + willHalt(CountDown.class));
        System.out.println("¿Se detiene 'CountUp'? " + willHalt(CountUp.class));
    }
}
