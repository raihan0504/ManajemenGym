// Main class untuk menjalankan sistem
public class GymManagementSystem {
    public static void main(String[] args) {
        System.out.println("Memulai Sistem Manajemen Gym...");
        CoreSystem coreSystem = new CoreSystem();
        coreSystem.start();
    }
}