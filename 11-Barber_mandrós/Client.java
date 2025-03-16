import java.util.Random;

class Client {
    private String nom;

    public Client(int id) {
        this.nom = "Client - " + id;
    }

    public void tallarseElCabell() {
        System.out.println("Tallant cabell a " + nom);
        try {
            Thread.sleep(1000 + new Random().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        if (Math.random() > 0.8) {
            return "";
        }
        return nom;
    }
}