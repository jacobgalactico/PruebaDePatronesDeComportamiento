public class CountDown {

    public static void main(String[] args) {
        int num = 10; // Este es el punto de partida del conteo regresivo.

        while (num > 0) {
            System.out.println(num);
            num--; // Decrementamos el número en 1.
        }

        System.out.println("El programa ha terminado."); // El programa se detiene cuando num llega a 0.
    }
}
