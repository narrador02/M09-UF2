import java.util.Random;

class Fumador extends Thread {
    private final Estanc estanc;
    private final int id;
    private Tabac tabac = null;
    private Llumi llumi = null;
    private Paper paper = null;
    private int fumades = 0;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    public void compraTabac() {
        try {
            System.out.println("Fumador " + id + " comprant Tabac");
            tabac = estanc.venTabac();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void compraLlumi() {
        try {
            System.out.println("Fumador " + id + " comprant Llumí");
            llumi = estanc.venLlumi();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void compraPaper() {
        try {
            System.out.println("Fumador " + id + " comprant Paper");
            paper = estanc.venPaper();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void fuma() {
        if (tabac != null && llumi != null && paper != null) {
            System.out.println("Fumador " + id + " fumant");
            try {
                Thread.sleep(500 + new Random().nextInt(501));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            fumades++;
            System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
            tabac = null;
            llumi = null;
            paper = null;
        }
    }

    @Override
    public void run() {
        while (fumades < 3) {
            if (tabac == null) compraTabac();
            if (llumi == null) compraLlumi();
            if (paper == null) compraPaper();
            fuma();
        }
    }
}