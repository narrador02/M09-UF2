import java.util.Random;

class Client {
    private String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public void tallarseElCabell() {
        System.out.println("Tallant cabell a " + nom);
        try {
            Thread.sleep(900 + new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}