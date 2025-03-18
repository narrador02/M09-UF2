class Barber implements Runnable {
    private String name;
    private Barberia barberia;

    public Barber(String name, Barberia barberia) {
        this.name = name;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized (barberia) {
                client = barberia.seguentClient();
                if (client == null) {
                    System.out.println("Ningú en espera");
                    System.out.println("Barber " + name + " dormint");
                    try {
                        synchronized (barberia.condBarber) {
                            barberia.condBarber.wait(); 
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
            System.out.println("Li toca al client " + client.getNom());
            client.tallarseElCabell();
            try {
                Thread.sleep(900 + (int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}