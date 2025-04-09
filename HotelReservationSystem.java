
import java.time.LocalDate;
import java.util.Scanner;

// Class utama sistem
public class HotelReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ReservationComponent reservationService;
    
    public static void main(String[] args) {
        // Inisialisasi komponen
        RoomManagementComponent roomService = new RoomManagementComponent();
        PaymentComponent paymentService = new PaymentComponent();
        NotificationComponent notificationService = new NotificationComponent();
        
        // Injeksi dependensi
        reservationService = new ReservationComponent(roomService, paymentService, notificationService);
        
        // Jalankan aplikasi
        runApplication();
    }
    
    private static void runApplication() {
        boolean exit = false;
        
        while (!exit) {
            printMenu();
            int choice = getIntInput("Pilih menu (1-5): ");
            
            switch (choice) {
                case 1:
                    createNewReservation();
                    break;
                case 2:
                    checkReservation();
                    break;
                case 3:
                    processPaymentMenu();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Terima kasih telah menggunakan Sistem Reservasi Hotel.");
                    break;
                default:
                    System.out.println("Menu tidak valid. Silakan coba lagi.");
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n===== SISTEM RESERVASI HOTEL =====");
        System.out.println("1. Buat Reservasi Baru");
        System.out.println("2. Cek Reservasi");
        System.out.println("3. Proses Pembayaran");
        System.out.println("4. Batalkan Reservasi");
        System.out.println("5. Keluar");
        System.out.println("=================================");
    }
    
    private static void createNewReservation() {
        System.out.println("\n--- BUAT RESERVASI BARU ---");
        
        String name = getStringInput("Nama Tamu: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("No. Telepon: ");
        
        System.out.println("\nPilih Tipe Kamar:");
        System.out.println("1. Standard (Rp 500.000/malam)");
        System.out.println("2. Deluxe (Rp 850.000/malam)");
        System.out.println("3. Suite (Rp 1.500.000/malam)");
        int roomChoice = getIntInput("Pilihan: ");
        
        String roomType;
        switch (roomChoice) {
            case 1: roomType = "Standard"; break;
            case 2: roomType = "Deluxe"; break;
            case 3: roomType = "Suite"; break;
            default: 
                System.out.println("Pilihan tidak valid. Menggunakan tipe Standard.");
                roomType = "Standard";
        }
        
        String checkInStr = getStringInput("Tanggal Check-in (yyyy-MM-dd): ");
        String checkOutStr = getStringInput("Tanggal Check-out (yyyy-MM-dd): ");
        
        LocalDate checkIn, checkOut;
        try {
            checkIn = LocalDate.parse(checkInStr);
            checkOut = LocalDate.parse(checkOutStr);
            
            if (checkIn.isAfter(checkOut) || checkIn.equals(checkOut)) {
                System.out.println("Tanggal check-out harus setelah check-in.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            return;
        }
        
        int guests = getIntInput("Jumlah Tamu: ");
        
        Reservation reservation = new Reservation(name, email, phone, roomType, checkIn, checkOut, guests);
        
        String reservationId = reservationService.createReservation(reservation);
        
        if (reservationId != null) {
            System.out.println("\nReservasi berhasil dibuat!");
            System.out.println("ID Reservasi: " + reservationId);
            System.out.println("Total Harga: Rp " + reservation.getTotalPrice());
            System.out.println("Silakan lakukan pembayaran untuk mengkonfirmasi reservasi.");
        } else {
            System.out.println("\nMaaf, tidak ada kamar " + roomType + " yang tersedia untuk tanggal yang dipilih.");
        }
    }
    
    private static void checkReservation() {
        System.out.println("\n--- CEK RESERVASI ---");
        String id = getStringInput("Masukkan ID Reservasi: ");
        
        Reservation reservation = reservationService.getReservation(id);
        
        if (reservation != null) {
            System.out.println("\nDetail Reservasi:");
            System.out.println("ID: " + reservation.getReservationId());
            System.out.println("Nama: " + reservation.getGuestName());
            System.out.println("Tipe Kamar: " + reservation.getRoomType());
            System.out.println("Check-in: " + reservation.getCheckInDate());
            System.out.println("Check-out: " + reservation.getCheckOutDate());
            System.out.println("Jumlah Tamu: " + reservation.getNumberOfGuests());
            System.out.println("Total Harga: Rp " + reservation.getTotalPrice());
            System.out.println("Status Pembayaran: " + (reservation.isPaid() ? "Lunas" : "Belum Dibayar"));
        } else {
            System.out.println("Reservasi tidak ditemukan.");
        }
    }
    
    private static void processPaymentMenu() {
        System.out.println("\n--- PROSES PEMBAYARAN ---");
        String id = getStringInput("Masukkan ID Reservasi: ");
        
        Reservation reservation = reservationService.getReservation(id);
        
        if (reservation != null) {
            if (reservation.isPaid()) {
                System.out.println("Reservasi ini sudah dibayar.");
                return;
            }
            
            System.out.println("Total yang harus dibayar: Rp " + reservation.getTotalPrice());
            System.out.println("\nPilih Metode Pembayaran:");
            System.out.println("1. Kartu Kredit");
            System.out.println("2. Transfer Bank");
            
            int paymentMethod = getIntInput("Pilihan: ");
            String paymentDetails;
            
            switch (paymentMethod) {
                case 1:
                    paymentDetails = "CC-" + getStringInput("Nomor Kartu Kredit: ");
                    break;
                case 2:
                    paymentDetails = "TF-" + getStringInput("Nomor Referensi Transfer: ");
                    break;
                default:
                    System.out.println("Metode pembayaran tidak valid.");
                    return;
            }
            
            boolean success = reservationService.processPayment(id, paymentDetails);
            
            if (success) {
                System.out.println("\nPembayaran berhasil! Reservasi telah dikonfirmasi.");
            } else {
                System.out.println("\nPembayaran gagal. Silakan coba lagi nanti.");
            }
        } else {
            System.out.println("Reservasi tidak ditemukan.");
        }
    }
    
    private static void cancelReservation() {
        System.out.println("\n--- BATALKAN RESERVASI ---");
        String id = getStringInput("Masukkan ID Reservasi: ");
        
        Reservation reservation = reservationService.getReservation(id);
        
        if (reservation != null) {
            System.out.println("\nDetail Reservasi:");
            System.out.println("ID: " + reservation.getReservationId());
            System.out.println("Nama: " + reservation.getGuestName());
            System.out.println("Check-in: " + reservation.getCheckInDate());
            System.out.println("Status Pembayaran: " + (reservation.isPaid() ? "Lunas" : "Belum Dibayar"));
            
            String confirm = getStringInput("\nApakah Anda yakin ingin membatalkan reservasi ini? (y/n): ");
            
            if (confirm.equalsIgnoreCase("y")) {
                boolean success = reservationService.cancelReservation(id);
                
                if (success) {
                    System.out.println("Reservasi berhasil dibatalkan.");
                    if (reservation.isPaid()) {
                        System.out.println("Pengembalian dana akan diproses dalam 3-5 hari kerja.");
                    }
                } else {
                    System.out.println("Gagal membatalkan reservasi.");
                }
            } else {
                System.out.println("Pembatalan dibatalkan.");
            }
        } else {
            System.out.println("Reservasi tidak ditemukan.");
        }
    }
    
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int value = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            return value;
        } catch (Exception e) {
            scanner.nextLine(); // Clear buffer
            return 0;
        }
    }
}