public class Filosof extends Thread {
    private static final int TEMPS_MENJAR_MIN = 1000;
    private static final int TEMPS_MENJAR_MAX = 2000;
    private static final int TEMPS_ESPERA_MIN = 500;
    private static final int TEMPS_ESPERA_MAX = 1000; 
    private static final int TEMPS_PENSAR_MIN = 1000; 
    private static final int TEMPS_PENSAR_MAX = 2000; 
    private static final int ITERACIONS_MAXIMES = 10; 
    private int id;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0;
    }

    @Override
    public void run() {
        try {
            for (int iteracio = 0; iteracio < ITERACIONS_MAXIMES; iteracio++) {
                pensar();
                menjar();
            }
        } catch (InterruptedException e) {
            System.out.println("Filòsof " + id + " ha estat interromput.");
            Thread.currentThread().interrupt();
        }
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }
    
    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + id + " està pensant.");
        Thread.sleep(getTempsAleatori(TEMPS_PENSAR_MIN, TEMPS_PENSAR_MAX));
    }

    private void menjar() throws InterruptedException {
        agafarForquilles();
        System.out.println("Filòsof " + id + " està menjant.");
        Thread.sleep(getTempsAleatori(TEMPS_MENJAR_MIN, TEMPS_MENJAR_MAX));
        deixarForquilles();
    }

    private void agafarForquilles() throws InterruptedException {
        agafarForquilla(forquillaEsquerra, "esquerra");
        agafarForquilla(forquillaDreta, "dreta");
    }

    private void agafarForquilla(Forquilla forquilla, String direccio) throws InterruptedException {
        synchronized (forquilla) {
            while (forquilla.isEnUs()) {
                gana++;
                System.out.println("Filòsof " + id + " no pot agafar la forquilla " + direccio + " " + forquilla.getNumero() + ". Gana: " + gana);
                forquilla.wait(getTempsAleatori(TEMPS_ESPERA_MIN, TEMPS_ESPERA_MAX));
            }
            forquilla.setEnUs(true);
            System.out.println("Filòsof " + id + " ha agafat la forquilla " + direccio + " " + forquilla.getNumero());
        }
    }

    private void deixarForquilles() {
        deixarForquilla(forquillaEsquerra, "esquerra");
        deixarForquilla(forquillaDreta, "dreta");
        gana = 0; // Resetejar la gana després de menjar
    }

    private void deixarForquilla(Forquilla forquilla, String direccio) {
        synchronized (forquilla) {
            forquilla.setEnUs(false);
            forquilla.notifyAll();
            System.out.println("Filòsof " + id + " ha deixat la forquilla " + direccio + " " + forquilla.getNumero());
        }
    }

    private long getTempsAleatori(int min, int max) {
        return (long) (Math.random() * (max - min) + min);
    }
}