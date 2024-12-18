public class MainDemoFil {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("MainDemoFil.main:");
        System.out.printf("Prioritat -> %d, Nom -> %s%n", currentThread.getPriority(), currentThread.getName());
        System.out.printf("toString() -> %s%n", currentThread.toString());
    }
}
