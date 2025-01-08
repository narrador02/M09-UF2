import java.util.Random;

public class Motor extends Thread {

    private int potenciaActual;
    private int potenciaObjectiu;
    private final Random generadorAleatori;

    public Motor(String nombre) {
        super(nombre);
        this.potenciaActual = 0;
        this.potenciaObjectiu = 0;
        this.generadorAleatori = new Random();
    }

    public synchronized void setPotencia(int potencia) {
        this.potenciaObjectiu = potencia;
        notify(); 
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    System.out.printf("%s: Incre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    System.out.printf("%s: Decre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                } else {
                    System.out.printf("%s: FerRes Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(generadorAleatori.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}