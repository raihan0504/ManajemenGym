class CoreSystem implements IMemberData, IScheduleData {
    private java.util.Map<Integer, Member> members = new java.util.HashMap<>();
    private java.util.Map<Integer, Schedule> schedules = new java.util.HashMap<>();
    private MembershipModule membershipModule;
    private SchedulingModule schedulingModule;
    private PaymentModule paymentModule;
    private ReportingModule reportingModule;
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    
    public CoreSystem() {
        // Pembentukan modul-modul
        paymentModule = new PaymentModule();
        
        membershipModule = new MembershipModule(this);
        schedulingModule = new SchedulingModule(this);
        reportingModule = new ReportingModule(this, this, paymentModule);
    }
    
    // Implementasi IMemberData
    @Override
    public void saveMember(Member member) {
        members.put(member.getId(), member);
    }
    
    @Override
    public Member getMember(int memberId) {
        return members.get(memberId);
    }
    
    @Override
    public void updateMember(Member member) {
        if (members.containsKey(member.getId())) {
            members.put(member.getId(), member);
        }
    }
    
    @Override
    public void deleteMember(int memberId) {
        members.remove(memberId);
    }
    
    // Implementasi IScheduleData
    @Override
    public void saveSchedule(Schedule schedule) {
        schedules.put(schedule.getId(), schedule);
    }
    
    @Override
    public Schedule getSchedule(int scheduleId) {
        return schedules.get(scheduleId);
    }
    
    @Override
    public void updateSchedule(Schedule schedule) {
        if (schedules.containsKey(schedule.getId())) {
            schedules.put(schedule.getId(), schedule);
        }
    }
    
    @Override
    public void deleteSchedule(int scheduleId) {
        schedules.remove(scheduleId);
    }
    
    public void start() {
        while (true) {
            System.out.println("\n===== SISTEM MANAJEMEN GYM =====");
            System.out.println("1. Manajemen Keanggotaan");
            System.out.println("2. Manajemen Jadwal");
            System.out.println("3. Manajemen Pembayaran");
            System.out.println("4. Laporan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Menghabiskan newline
            
            switch (choice) {
                case 1:
                    membershipModule.showMenu();
                    break;
                case 2:
                    schedulingModule.showMenu();
                    break;
                case 3:
                    paymentModule.showMenu();
                    break;
                case 4:
                    reportingModule.showMenu();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan Sistem Manajemen Gym!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}