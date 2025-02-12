public class Filosof extends Thread {
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

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    private void menjar() throws InterruptedException {
        synchronized (forquillaEsquerra) {
            while (forquillaEsquerra.isEnUs()) {
                gana++; 
                System.out.println("Filòsof: fil" + id + " no pot agafar la forquilla esquerra " + forquillaEsquerra.getNumero() + ". Gana: " + gana);
                forquillaEsquerra.wait((long) (Math.random() * 500 + 500)); // Espera entre 0.5s y 1s
            }
            forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof: fil" + id + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
    
            synchronized (forquillaDreta) {
                while (forquillaDreta.isEnUs()) {
                    gana++; 
                    System.out.println("Filòsof: fil" + id + " no pot agafar la forquilla dreta " + forquillaDreta.getNumero() + ". Gana: " + gana);
                    forquillaDreta.wait((long) (Math.random() * 500 + 500)); // Espera entre 0.5s y 1s
                }
                forquillaDreta.setEnUs(true);
                System.out.println("Filòsof: fil" + id + " agafa la forquilla dreta " + forquillaDreta.getNumero());
    
                System.out.println("Filòsof: fil" + id + " menja");
                Thread.sleep((long) (Math.random() * 1000 + 1000)); // Come entre 1s y 2s
    
                forquillaDreta.setEnUs(false);
                forquillaDreta.notifyAll();
            }
    
            forquillaEsquerra.setEnUs(false);
            forquillaEsquerra.notifyAll();
            gana = 0; // Resetea el hambre después de comer
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof: fil" + id + " pensant");
        Thread.sleep((long) (Math.random() * 1000 + 1000)); // Piwnsa entre 1s y 2s
    }

    @Override
    public void run() {
        try {
            int iteracions = 0;
            while (iteracions < 10) { // Para tras 10 iteraciones
                pensar();
                menjar();
                iteracions++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Filòsof: fil" + id + " ha estat interromput");
        }
    }
}