public class Fil extends Thread {
    private final String nombre;
    private final int delay;
    private static final Object lock = new Object();
    private static boolean turnoJuan = true; // Alternancia controlada
    private final boolean alternancia;       // Flag para modo alternancia

    public Fil(String nombre, int delay, boolean alternancia) {
        this.nombre = nombre;
        this.delay = delay;
        this.alternancia = alternancia;
    }

    @Override
    public void run() {
        if (alternancia) {
            ejecutarAlternancia();
        } else {
            ejecutarNormal();
        }
    }

    private void ejecutarNormal() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nombre + " " + i);
            try {
                Thread.sleep(delay * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Termina el hilo " + nombre);
    }

    private void ejecutarAlternancia() {
        for (int i = 1; i <= 9; i++) {
            synchronized (lock) {
                while (turnoJuan != nombre.equals("Juan")) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(nombre + " " + i);
                turnoJuan = !turnoJuan;
                lock.notifyAll();
            }
        }
        System.out.println("Termina el hilo " + nombre);
    }
}
