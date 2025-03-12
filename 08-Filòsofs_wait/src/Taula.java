public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;

    public Taula(int numFilòsofs) {
        filosofs = new Filosof[numFilòsofs];
        forquilles = new Forquilla[numFilòsofs];

        for (int i = 0; i < numFilòsofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilòsofs; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilòsofs];
            filosofs[i] = new Filosof(i, esquerra, dreta);
        }
    }

    public void showTaula() {
        for (int i = 0; i < filosofs.length; i++) {
            System.out.println("Comensal: fil" + i + " esq:" + filosofs[i].getForquillaEsquerra().getNumero() +
                    " dret:" + filosofs[i].getForquillaDreta().getNumero());
        }
    }

    public void cridaraTaula() {
        for (Filosof filòsof : filosofs) {
            filòsof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridaraTaula();
    }
}