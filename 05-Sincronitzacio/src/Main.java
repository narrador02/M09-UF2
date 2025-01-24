import java.util.Random;

class Compte {
    private static Compte instance;
    private float saldo = 0.0f;

    private Compte() {}

    public static synchronized Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public synchronized void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        if (saldo >= quantitat) {
            saldo -= quantitat;
        }
    }

    public synchronized float getSaldo() {
        return saldo;
    }
}

class Soci extends Thread {
    private final Compte compte;
    private final float aportacio = 10f;
    private final int maxAnys = 10;
    private final Random random = new Random();

    public Soci(Compte compte) {
        this.compte = compte;
    }

    @Override
    public void run() {
        for (int any = 0; any < maxAnys; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                synchronized (compte) {
                    compte.ingressar(aportacio);
                    compte.retirar(aportacio);
                }
                try {
                    Thread.sleep(random.nextInt(50));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

class Associacio {
    private final int numSocis = 1000;
    private final Soci[] socis;
    private final Compte compte;

    public Associacio() {
        compte = Compte.getInstance();
        socis = new Soci[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci(compte);
        }
    }

    public void iniciaCompteTempsSocis() {
        for (Soci soci : socis) {
            soci.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci soci : socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
    }
}