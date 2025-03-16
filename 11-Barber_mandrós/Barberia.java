import java.util.LinkedList;
import java.util.Queue;

class Barberia implements Runnable {
    private Queue<Client> salaEspera;
    private int maxSillas;
    public final Object condBarber = new Object();
    private static Barberia instancia;

    public Barberia(int maxSillas) {
        this.maxSillas = maxSillas;
        this.salaEspera = new LinkedList<>();
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }

    public void entrarClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < maxSillas) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }
            Thread.sleep(10000);
            for (int i = 11; i <= 20; i++) {
                entrarClient(new Client(i));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pedro", barberia);

        Thread barberThread = new Thread(barber);
        Thread barberiaThread = new Thread(barberia);

        barberThread.start();
        barberiaThread.start();
    }
}