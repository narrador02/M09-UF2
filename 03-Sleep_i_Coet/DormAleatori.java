import java.util.Random;

public class DormAleatori extends Thread {

    private final long instantCreacio; 
    private final Random generadorAleatori;

    public DormAleatori(String nombre) {
        super(nombre); // Para mantener el nombre del hilo
        this.instantCreacio = System.currentTimeMillis();
        this.generadorAleatori = new Random();
    }

    @Override
    public void run() {
        long instantInici = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            int intervalAleatori = generadorAleatori.nextInt(1000);
            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long tempsTotal = System.currentTimeMillis() - instantInici;
            System.out.printf("%s(%d) a dormir %dms total %d%n", getName(), i, intervalAleatori, tempsTotal);
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");   
        joan.start();
        pep.start();  
        System.out.println("---------- Fin del main ----------");    
        try {
            joan.join();
            pep.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    