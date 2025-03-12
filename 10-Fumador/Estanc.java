import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Tabac {}
class Paper {}
class Llumi {}

public class Estanc {
    private final List<Tabac> tabac = new ArrayList<>();
    private final List<Paper> paper = new ArrayList<>();
    private final List<Llumi> llumi = new ArrayList<>();
    private boolean obert = true;
    private final Random random = new Random();

    public synchronized void nouSubministrament() {
        while (!obert) return;

        int tipus = random.nextInt(3);
        switch (tipus) {
            case 0 -> addTabac();
            case 1 -> addPaper();
            case 2 -> addLlumi();
        }
        notifyAll();
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint tabac");
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint paper");
    }

    public synchronized void addLlumi() {
        llumi.add(new Llumi());
        System.out.println("Afegint llumí");
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabac.isEmpty()) wait();
        return tabac.remove(0);
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (paper.isEmpty()) wait();
        return paper.remove(0);
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumi.isEmpty()) wait();
        return llumi.remove(0);
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();
        System.out.println("Estanc tancat");
    }

    public void executar() {
        System.out.println("Estanc obert");
        while (obert) {
            try {
                Thread.sleep(500 + random.nextInt(1000));
                nouSubministrament();
            } catch (InterruptedException ignored) {}
        }
    }
}

class Fumador implements Runnable {
    private final Estanc estanc;
    private final int id;
    private int fumades = 0;
    private final Random random = new Random();

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    private void fuma() throws InterruptedException {
        System.out.println("Fumador " + id + " fumant");
        Thread.sleep(500 + random.nextInt(500));
        fumades++;
        System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
    }

    private void comprar() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant tabac");
        estanc.venTabac();
        System.out.println("Fumador " + id + " comprant paper");
        estanc.venPaper();
        System.out.println("Fumador " + id + " comprant llumí");
        estanc.venLlumi();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                comprar();
                fuma();
            }
        } catch (InterruptedException ignored) {}
    }
}