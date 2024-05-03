package clases;

public class CountUp {
    public static void main(String[] args) {
        int num = 1;

        while (true) {
            System.out.println(num);
            num++;
            try {
                Thread.sleep(1000);  // Duerme 1 segundo en cada iteración
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
