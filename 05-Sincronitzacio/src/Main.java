import java.util.Random;

// Singleton per a la classe Compte
class Compte {
    private static Compte instance;
    private float saldo = 0.0f;

    private Compte() {}

    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public void retirar(float quantitat) {
        if (saldo >= quantitat) {
            saldo -= quantitat;
        }
    }

    public float getSaldo() {
        return saldo;
    }
}

// Classe Soci que realitza ingressos i retirades
class Soci extends Thread {
    private Compte compte;
    private final float aportacio = 10f;
    private final int esperaMax = 100;
    private final int maxAnys = 10;
    private Random random = new Random();

    public Soci(Compte compte) {
        this.compte = compte;
    }

    @Override
    public void run() {
        for (int any = 0; any < maxAnys; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0) {
                    compte.ingressar(aportacio);
                } else {
                    compte.retirar(aportacio);
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Classe Associació que gestiona els socis
class Associacio {
    private final int numSocis = 1000;
    private Soci[] socis;
    private Compte compte;

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
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancComptes() {
        System.out.printf("Saldo: %.2f\n", compte.getSaldo());
    }
}

public class Main {
    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}