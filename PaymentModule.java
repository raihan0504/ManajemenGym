class PaymentModule implements IPaymentProcess {
    private java.util.Map<Integer, Payment> payments = new java.util.HashMap<>();
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int nextPaymentId = 1;
    
    @Override
    public boolean processPayment(Payment payment) {
        payments.put(payment.getId(), payment);
        return true;
    }
    
    @Override
    public void refundPayment(int paymentId) {
        if (payments.containsKey(paymentId)) {
            payments.remove(paymentId);
        }
    }
    
    @Override
    public Payment getPaymentDetail(int paymentId) {
        return payments.get(paymentId);
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n===== MENU PEMBAYARAN =====");
            System.out.println("1. Proses Pembayaran Baru");
            System.out.println("2. Lihat Detail Pembayaran");
            System.out.println("3. Proses Pengembalian Dana");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            
            switch (choice) {
                case 1:
                    processNewPayment();
                    break;
                case 2:
                    viewPaymentDetails();
                    break;
                case 3:
                    processRefund();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private void processNewPayment() {
        System.out.println("\n--- PROSES PEMBAYARAN BARU ---");
        
        int id = nextPaymentId++;
        
        System.out.print("ID Anggota: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        System.out.print("Jumlah Pembayaran: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Menghabiskan newline
        
        System.out.print("Tanggal Pembayaran (DD/MM/YYYY): ");
        String paymentDate = scanner.nextLine();
        
        System.out.print("Metode Pembayaran (Tunai/Kartu/Transfer): ");
        String paymentMethod = scanner.nextLine();
        
        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();
        
        Payment newPayment = new Payment(id, memberId, amount, paymentDate, paymentMethod, description);
        boolean success = processPayment(newPayment);
        
        if (success) {
            System.out.println("Pembayaran berhasil diproses dengan ID: " + id);
        } else {
            System.out.println("Gagal memproses pembayaran.");
        }
    }
    
    private void viewPaymentDetails() {
        System.out.print("\nMasukkan ID Pembayaran: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Payment payment = getPaymentDetail(id);
        if (payment != null) {
            System.out.println("\n--- DETAIL PEMBAYARAN ---");
            System.out.println(payment);
        } else {
            System.out.println("Pembayaran dengan ID " + id + " tidak ditemukan.");
        }
    }
    
    private void processRefund() {
        System.out.print("\nMasukkan ID Pembayaran yang akan dikembalikan: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Payment payment = getPaymentDetail(id);
        if (payment != null) {
            System.out.print("Anda yakin ingin mengembalikan dana sebesar " + payment.getAmount() + " kepada anggota ID " + payment.getMemberId() + "? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                refundPayment(id);
                System.out.println("Pengembalian dana berhasil diproses.");
            } else {
                System.out.println("Pengembalian dana dibatalkan.");
            }
        } else {
            System.out.println("Pembayaran dengan ID " + id + " tidak ditemukan.");
        }
    }
}