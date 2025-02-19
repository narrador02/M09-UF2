public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;

    public Taula(int numFilòsofs) {
        filosofs = new Filosof[numFilòsofs];
        forquilles = new Forquilla[numFilòsofs];

        // Crear les forquilles
        for (int i = 0; i < numFilòsofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        // Assignar forquilles als filòsofs
        for (int i = 0; i < numFilòsofs; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilòsofs];
            filosofs[i] = new Filosof(i, esquerra, dreta);
        }
    }

    public void mostrarTaula() {
        for (int i = 0; i < filosofs.length; i++) {
            System.out.println("Filòsof " + i + " té forquilla esquerra: " + filosofs[i].getForquillaEsquerra().getNumero() +
                    " i forquilla dreta: " + filosofs[i].getForquillaDreta().getNumero());
        }
    }

    public void iniciarSopar() {
        for (Filosof filosof : filosofs) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.mostrarTaula();
        taula.iniciarSopar();
    }
}