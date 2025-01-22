import java.util.Random;

class Treballador extends Thread {
    private final float nouAnualBrut;
    private final int edatIniciTreball;
    private final int edatFiTreball;
    private int edatActual;
    private float cobrat;
    private final Random rnd;

    public Treballador(String nom, float nouAnualBrut, int edatIniciTreball, int edatFiTreball) {
        super(nom);
        this.nouAnualBrut = nouAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;
        this.edatActual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    private void cobra() {
        cobrat += nouAnualBrut / 12;
    }

    private void pagaImpuestos() {
        cobrat -= (nouAnualBrut / 12) * 0.24; // Aplica impuestos solo al salario mensual
    }

    @Override
    public void run() {
        for (edatActual = edatIniciTreball; edatActual <= edatFiTreball; edatActual++) {
            for (int mes = 0; mes < 12; mes++) {
                cobra();
                pagaImpuestos();
            }
        }
    }

    public float getCobrado() {
        return cobrat;
    }

    public int getEdad() {
        return edatFiTreball;
    }
}

class Administracio {
    private static final int NUM_POBLACIO_ACTIVA = 50;
    private final Treballador[] poblacioActiva;

    public Administracio() {
        poblacioActiva = new Treballador[NUM_POBLACIO_ACTIVA];
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            poblacioActiva[i] = new Treballador("Ciutadà-" + i, 25000, 20, 65);
        }
    }

    public void ejecutarSimulacion() {
        for (Treballador t : poblacioActiva) {
            t.start();
        }

        for (Treballador t : poblacioActiva) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mostrarEstadisticas();
    }

    private void mostrarEstadisticas() {
        for (Treballador t : poblacioActiva) {
            System.out.printf("%s -> edat: %d / total: %.2f\n", t.getName(), t.getEdad(), t.getCobrado());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.ejecutarSimulacion();
    }
}