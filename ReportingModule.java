class ReportingModule implements IReportGenerator {
    private IMemberData memberData;
    private IScheduleData scheduleData;
    private PaymentModule paymentModule;
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    
    public ReportingModule(IMemberData memberData, IScheduleData scheduleData, PaymentModule paymentModule) {
        this.memberData = memberData;
        this.scheduleData = scheduleData;
        this.paymentModule = paymentModule;
    }
    
    @Override
    public String generateMembershipReport() {
        StringBuilder report = new StringBuilder();
        report.append("===== LAPORAN KEANGGOTAAN =====\n\n");
        report.append("Data ini dihasilkan pada: ").append(java.time.LocalDate.now()).append("\n\n");
        report.append("Total anggota: [placeholder]\n");
        report.append("Distribusi tipe keanggotaan:\n");
        report.append("- Reguler: [placeholder]\n");
        report.append("- Premium: [placeholder]\n");
        report.append("- VIP: [placeholder]\n\n");
        report.append("Keanggotaan yang akan berakhir dalam 30 hari ke depan: [placeholder]\n");
        return report.toString();
    }
    
    @Override
    public String generateFinancialReport() {
        StringBuilder report = new StringBuilder();
        report.append("===== LAPORAN KEUANGAN =====\n\n");
        report.append("Data ini dihasilkan pada: ").append(java.time.LocalDate.now()).append("\n\n");
        report.append("Total pendapatan: [placeholder]\n");
        report.append("Pendapatan berdasarkan metode pembayaran:\n");
        report.append("- Tunai: [placeholder]\n");
        report.append("- Kartu: [placeholder]\n");
        report.append("- Transfer: [placeholder]\n\n");
        report.append("Pengembalian dana: [placeholder]\n");
        report.append("Pendapatan bersih: [placeholder]\n");
        return report.toString();
    }
    
    @Override
    public String generateAttendanceReport() {
        StringBuilder report = new StringBuilder();
        report.append("===== LAPORAN KEHADIRAN =====\n\n");
        report.append("Data ini dihasilkan pada: ").append(java.time.LocalDate.now()).append("\n\n");
        report.append("Rata-rata kehadiran harian: [placeholder]\n");
        report.append("Kelas dengan kehadiran tertinggi: [placeholder]\n");
        report.append("Kelas dengan kehadiran terendah: [placeholder]\n");
        report.append("Pelatih dengan kelas terbanyak: [placeholder]\n");
        return report.toString();
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n===== MENU PELAPORAN =====");
            System.out.println("1. Laporan Keanggotaan");
            System.out.println("2. Laporan Keuangan");
            System.out.println("3. Laporan Kehadiran");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            
            switch (choice) {
                case 1:
                    System.out.println("\n" + generateMembershipReport());
                    break;
                case 2:
                    System.out.println("\n" + generateFinancialReport());
                    break;
                case 3:
                    System.out.println("\n" + generateAttendanceReport());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}