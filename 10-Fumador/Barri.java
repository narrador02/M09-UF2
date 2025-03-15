public class Barri {
    public static void main(String[] args) {
        Estanc estanc = new Estanc();
        Fumador[] fumadors = {new Fumador(estanc, 0), new Fumador(estanc, 1), new Fumador(estanc, 2)};

        System.out.println("Estanc obert");
        estanc.start();

        for (Fumador f : fumadors) f.start();

        for (Fumador f : fumadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        estanc.tancarEstanc();
    }
}