public class Principal {
    public static void main(String[] args) {
        System.out.println("Termina thread main");

        // Comportamiento 1: Intercalado Equitativo
        System.out.println("=== Comportamiento 1: Intercalado ===");
        Fil juan1 = new Fil("Juan", 2, false);
        Fil pepe1 = new Fil("Pepe", 2, false);
        juan1.start();
        pepe1.start();
        try {
            juan1.join();
            pepe1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comportamiento 2: Pepe Primero
        System.out.println("=== Comportamiento 2: Pepe Primero ===");
        Fil juan2 = new Fil("Juan", 10, false); // Delay mayor para Juan
        Fil pepe2 = new Fil("Pepe", 1, false);  // Delay menor para Pepe
        pepe2.start();
        juan2.start();
        try {
            pepe2.join();
            juan2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comportamiento 3: Alternancia Estricta
        System.out.println("=== Comportamiento 3: Alternancia Estricta ===");
        Fil juan3 = new Fil("Juan", 0, true); // Modo alternancia
        Fil pepe3 = new Fil("Pepe", 0, true); // Modo alternancia
        juan3.start();
        pepe3.start();
        try {
            juan3.join();
            pepe3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
