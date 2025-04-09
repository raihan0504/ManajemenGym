import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GymManagementApp {
    public static void main(String[] args) {
        System.out.println("Starting Gym Management System");
        
        GymManagementSystem gymSystem = new GymManagementSystem();
        gymSystem.initialize();
        
        // Tampilkan laporan
        gymSystem.displayMemberReport();
        gymSystem.displayClassReport();
        gymSystem.displayEquipmentReport();
        
        // Demo sederhana untuk menambahkan anggota baru
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n===== GYM MANAGEMENT SYSTEM =====");
            System.out.println("1. Tambah Anggota Baru");
            System.out.println("2. Tampilkan Semua Anggota");
            System.out.println("3. Tambah Kelas Fitness");
            System.out.println("4. Daftarkan Anggota ke Kelas");
            System.out.println("5. Tambah Peralatan Gym");
            System.out.println("6. Proses Pembayaran");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("ID Anggota: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Nomor Kontak: ");
                    String contact = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Tipe Keanggotaan (1-BASIC, 2-STANDARD, 3-PREMIUM, 4-VIP): ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    MembershipType type;
                    switch (typeChoice) {
                        case 1: type = MembershipType.BASIC; break;
                        case 2: type = MembershipType.STANDARD; break;
                        case 3: type = MembershipType.PREMIUM; break;
                        case 4: type = MembershipType.VIP; break;
                        default: type = MembershipType.BASIC;
                    }
                    
                    Member newMember = new Member(memberId, name, contact, email, type);
                    boolean added = gymSystem.getMemberManager().registerMember(newMember);
                    if (added) {
                        System.out.println("Anggota berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan anggota!");
                    }
                    break;
                    
                case 2:
                    List<Member> members = gymSystem.getMemberManager().getAllMembers();
                    System.out.println("\n===== DAFTAR ANGGOTA =====");
                    for (Member member : members) {
                        System.out.println(member);
                    }
                    break;
                    
                case 3:
                    System.out.print("ID Kelas: ");
                    String classId = scanner.nextLine();
                    System.out.print("Nama Kelas: ");
                    String className = scanner.nextLine();
                    System.out.print("Instruktur: ");
                    String instructor = scanner.nextLine();
                    System.out.print("Kapasitas: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    LocalDateTime scheduleTime = LocalDateTime.now().plusDays(1);
                    FitnessClass newClass = new FitnessClass(classId, className, instructor, scheduleTime, capacity);
                    boolean classAdded = gymSystem.getClassManager().createClass(newClass);
                    if (classAdded) {
                        System.out.println("Kelas berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan kelas!");
                    }
                    break;
                    
                case 4:
                    System.out.print("ID Anggota: ");
                    String mid = scanner.nextLine();
                    System.out.print("ID Kelas: ");
                    String cid = scanner.nextLine();
                    
                    boolean registered = gymSystem.getClassManager().registerMemberToClass(mid, cid);
                    if (registered) {
                        System.out.println("Anggota berhasil didaftarkan ke kelas!");
                    } else {
                        System.out.println("Gagal mendaftarkan anggota ke kelas!");
                    }
                    break;
                    
                case 5:
                    System.out.print("ID Peralatan: ");
                    String equipId = scanner.nextLine();
                    System.out.print("Nama Peralatan: ");
                    String equipName = scanner.nextLine();
                    System.out.print("Tipe (1-CARDIO, 2-STRENGTH, 3-FLEXIBILITY, 4-FREE_WEIGHT): ");
                    int equipTypeChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    EquipmentType equipType;
                    switch (equipTypeChoice) {
                        case 1: equipType = EquipmentType.CARDIO; break;
                        case 2: equipType = EquipmentType.STRENGTH; break;
                        case 3: equipType = EquipmentType.FLEXIBILITY; break;
                        case 4: equipType = EquipmentType.FREE_WEIGHT; break;
                        default: equipType = EquipmentType.CARDIO;
                    }
                    
                    Equipment newEquip = new Equipment(equipId, equipName, equipType, LocalDate.now());
                    boolean equipAdded = gymSystem.getEquipmentManager().addEquipment(newEquip);
                    if (equipAdded) {
                        System.out.println("Peralatan berhasil ditambahkan!");
                    } else {
                        System.out.println("Gagal menambahkan peralatan!");
                    }
                    break;
                    
                case 6:
                    System.out.print("ID Anggota: ");
                    String pmtMemberId = scanner.nextLine();
                    System.out.print("Jumlah Pembayaran: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    
                    boolean paymentProcessed = gymSystem.getPaymentManager().processMembershipPayment(pmtMemberId, amount);
                    if (paymentProcessed) {
                        System.out.println("Pembayaran berhasil diproses!");
                    } else {
                        System.out.println("Gagal memproses pembayaran!");
                    }
                    break;
                    
                case 0:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan Sistem Manajemen Gym!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        
        scanner.close();
    }
}