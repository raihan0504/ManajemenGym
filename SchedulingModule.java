public class SchedulingModule {
    private IScheduleData scheduleData;
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static int nextScheduleId = 1;
    
    public SchedulingModule(IScheduleData scheduleData) {
        this.scheduleData = scheduleData;
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n===== MENU PENJADWALAN =====");
            System.out.println("1. Tambah Jadwal Kelas Baru");
            System.out.println("2. Lihat Detail Jadwal");
            System.out.println("3. Perbarui Jadwal");
            System.out.println("4. Hapus Jadwal");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            
            switch (choice) {
                case 1:
                    addNewSchedule();
                    break;
                case 2:
                    viewScheduleDetails();
                    break;
                case 3:
                    updateSchedule();
                    break;
                case 4:
                    deleteSchedule();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private void addNewSchedule() {
        System.out.println("\n--- TAMBAH JADWAL KELAS BARU ---");
        
        int id = nextScheduleId++;
        
        System.out.print("Nama Kelas: ");
        String className = scanner.nextLine();
        
        System.out.print("Pelatih: ");
        String trainer = scanner.nextLine();
        
        System.out.print("Tanggal (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        
        System.out.print("Waktu Mulai (HH:MM): ");
        String startTime = scanner.nextLine();
        
        System.out.print("Waktu Selesai (HH:MM): ");
        String endTime = scanner.nextLine();
        
        System.out.print("Kapasitas: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Schedule newSchedule = new Schedule(id, className, trainer, date, startTime, endTime, capacity);
        scheduleData.saveSchedule(newSchedule);
        
        System.out.println("Jadwal baru berhasil ditambahkan dengan ID: " + id);
    }
    
    private void viewScheduleDetails() {
        System.out.print("\nMasukkan ID Jadwal: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Schedule schedule = scheduleData.getSchedule(id);
        if (schedule != null) {
            System.out.println("\n--- DETAIL JADWAL ---");
            System.out.println(schedule);
        } else {
            System.out.println("Jadwal dengan ID " + id + " tidak ditemukan.");
        }
    }
    
    private void updateSchedule() {
        System.out.print("\nMasukkan ID Jadwal yang akan diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Schedule schedule = scheduleData.getSchedule(id);
        if (schedule != null) {
            System.out.println("\n--- PERBARUI JADWAL ---");
            System.out.println("Data saat ini:");
            System.out.println(schedule);
            
            System.out.print("\nNama Kelas baru (kosongkan jika tidak berubah): ");
            String className = scanner.nextLine();
            if (!className.isEmpty()) {
                schedule.setClassName(className);
            }
            
            System.out.print("Pelatih baru (kosongkan jika tidak berubah): ");
            String trainer = scanner.nextLine();
            if (!trainer.isEmpty()) {
                schedule.setTrainer(trainer);
            }
            
            System.out.print("Tanggal baru (kosongkan jika tidak berubah): ");
            String date = scanner.nextLine();
            if (!date.isEmpty()) {
                schedule.setDate(date);
            }
            
            System.out.print("Waktu Mulai baru (kosongkan jika tidak berubah): ");
            String startTime = scanner.nextLine();
            if (!startTime.isEmpty()) {
                schedule.setStartTime(startTime);
            }
            
            System.out.print("Waktu Selesai baru (kosongkan jika tidak berubah): ");
            String endTime = scanner.nextLine();
            if (!endTime.isEmpty()) {
                schedule.setEndTime(endTime);
            }
            
            System.out.print("Kapasitas baru (0 jika tidak berubah): ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            if (capacity > 0) {
                schedule.setCapacity(capacity);
            }
            
            scheduleData.updateSchedule(schedule);
            System.out.println("Jadwal berhasil diperbarui.");
        } else {
            System.out.println("Jadwal dengan ID " + id + " tidak ditemukan.");
        }
    }
    
    private void deleteSchedule() {
        System.out.print("\nMasukkan ID Jadwal yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Menghabiskan newline
        
        Schedule schedule = scheduleData.getSchedule(id);
        if (schedule != null) {
            System.out.print("Anda yakin ingin menghapus jadwal " + schedule.getClassName() + " pada " + schedule.getDate() + "? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                scheduleData.deleteSchedule(id);
                System.out.println("Jadwal berhasil dihapus.");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }
        } else {
            System.out.println("Jadwal dengan ID " + id + " tidak ditemukan.");
        }
    }
}