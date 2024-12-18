import java.util.Random;

class Futbolista extends Thread {

    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int goles;
    private int tiros;
    private final Random random; 

    public Futbolista(String nombre) {
        super(nombre); // Nombre del jugador
        this.goles = 0;
        this.tiros = 0;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            tiros++;
            if (random.nextFloat() < PROBABILITAT) {
                goles++;
            }
        }
    }

    public int getGoles() {
        return goles;
    }

    public int getTiros() {
        return tiros;
    }

    public static void main(String[] args) {
        String[] nombresJugadores = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};
        Futbolista[] futbolistas = new Futbolista[nombresJugadores.length];

        System.out.println("Inicio de los tiros -----");

        // Crear hilos
        for (int i = 0; i < nombresJugadores.length; i++) {
            futbolistas[i] = new Futbolista(nombresJugadores[i]);
        }

        // Iniciar hilos
        for (Futbolista futbolista : futbolistas) {
            futbolista.start();
        }

        // Esperar a que terminen todos los hilos
        for (Futbolista futbolista : futbolistas) {
            try {
                futbolista.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fin de los tiros --------");

        System.out.println("--- Estadísticas --------");
        for (Futbolista futbolista : futbolistas) {
            System.out.println(futbolista.getName() + " -> " + futbolista.getGoles() + " goles");
        }
    }
}
