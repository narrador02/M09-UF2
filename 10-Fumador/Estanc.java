import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Estanc extends Thread {
    private final List<Tabac> tabacs = new ArrayList<>();
    private final List<Paper> papers = new ArrayList<>();
    private final List<Llumi> llumins = new ArrayList<>();
    private boolean tancat = false;
    private final Random random = new Random();

    private synchronized void addTabac(Tabac tabac) {
        tabacs.add(tabac);
        System.out.println("Afegint tabac");
        notifyAll();
    }

    private synchronized void addPaper(Paper paper) {
        papers.add(paper);
        System.out.println("Afegint Paper");
        notifyAll();
    }

    private synchronized void addLlumi(Llumi llumi) {
        llumins.add(llumi);
        System.out.println("Afegint Llumí");
        notifyAll();
    }

    public synchronized void nouSubministrament() {
        int tipus = random.nextInt(3);
        switch (tipus) {
            case 0 -> addTabac(new Tabac());
            case 1 -> addPaper(new Paper());
            case 2 -> addLlumi(new Llumi());
        }
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabacs.isEmpty() && !tancat) wait();
        return tabacs.isEmpty() ? null : tabacs.remove(0);
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (papers.isEmpty() && !tancat) wait();
        return papers.isEmpty() ? null : papers.remove(0);
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumins.isEmpty() && !tancat) wait();
        return llumins.isEmpty() ? null : llumins.remove(0);
    }

    public synchronized void tancarEstanc() {
        tancat = true;
        notifyAll();
        this.interrupt();
        System.out.println("Estanc tancat");
    }

    @Override
    public void run() {
        while (!tancat) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(501));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}