import java.util.Scanner;

public class Coet {

    private final Motor[] motors;

    public Coet() {
        this.motors = new Motor[4];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor("Motor " + i);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public void passaAPotencia(int potencia) {
        for (Motor motor : motors) {
            motor.setPotencia(potencia);
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        Scanner scanner = new Scanner(System.in);
        int potencia = -1;

        System.out.println("Introdueix la potència objectiu (0 a 10, 0 per acabar):");

        while (scanner.hasNextInt()) {
            potencia = scanner.nextInt();

            // Comprobar si la potencia está en el rango permitido
            if (potencia < 0 || potencia > 10) {
                System.out.println("Potència incorrecta. Introdueix un valor entre 0 i 10.");
                continue; // Solicitar una nueva entrada
            }

            coet.passaAPotencia(potencia);

            if (potencia == 0) {
                break; // Salir si la potencia es 0
            }

            System.out.print("Introdueix la potència objectiu (0 a 10, 0 per acabar): ");
        }

        scanner.close();
        System.out.println("Simulació acabada.");
    }
}