package clases;

public class CountDown {

    public static void main(String[] args) {
        int num = 10;

        while (num > 0) {
            System.out.println(num);
            num--; // Restamos 1.
        }

        System.out.println("El programa ha terminado."); // El programa se detiene cuando num llega a 0.
    }
}
