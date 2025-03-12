public class Barri {
    public static void main(String[] args) throws InterruptedException {
        Estanc estanc = new Estanc();
        Thread estancThread = new Thread(estanc::executar);
        estancThread.start();

        Fumador[] fumadors = new Fumador[3];
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
            threads[i] = new Thread(fumadors[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        estanc.tancarEstanc();
    }
}