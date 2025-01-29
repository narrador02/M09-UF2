public class Assistent extends Thread {
    private String nom;
    private Esdeveniment esdeveniment;

    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Math.random() < 0.5) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}