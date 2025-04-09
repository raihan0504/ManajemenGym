
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// Kelas utama yang menghubungkan semua komponen
class GymManagementSystem {
    private IMemberManagement memberManager;
    private IClassManagement classManager;
    private IEquipmentManagement equipmentManager;
    private IPaymentManagement paymentManager;
    private ILogger logger;
    private INotification notifier;
    private IValidation validator;
    
    public GymManagementSystem() {
        // Inisialisasi komponen-komponen
        this.logger = new ConsoleLogger();
        this.validator = new MemberValidator();
        this.memberManager = new MemberManager(logger, validator);
        this.notifier = new EmailNotificationService(memberManager);
        this.classManager = new ClassManager(memberManager, logger, notifier);
        this.equipmentManager = new EquipmentManager(logger);
        this.paymentManager = new PaymentManager(memberManager, logger, notifier);
    }
    
    // Getter untuk komponen-komponen
    public IMemberManagement getMemberManager() { return memberManager; }
    public IClassManagement getClassManager() { return classManager; }
    public IEquipmentManagement getEquipmentManager() { return equipmentManager; }
    public IPaymentManagement getPaymentManager() { return paymentManager; }
    
    // Metode untuk demo
    public void initialize() {
        // Tambahkan beberapa data dummy untuk demo
        Member member1 = new Member("M001", "John Doe", "08123456789", "john@example.com", MembershipType.PREMIUM);
        Member member2 = new Member("M002", "Jane Smith", "08987654321", "jane@example.com", MembershipType.STANDARD);
        
        memberManager.registerMember(member1);
        memberManager.registerMember(member2);
        
        LocalDateTime yogaClassTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        FitnessClass yogaClass = new FitnessClass("C001", "Morning Yoga", "Instructor A", yogaClassTime, 15);
        
        LocalDateTime zumbaClassTime = LocalDateTime.now().plusDays(2).withHour(18).withMinute(30);
        FitnessClass zumbaClass = new FitnessClass("C002", "Evening Zumba", "Instructor B", zumbaClassTime, 20);
        
        classManager.createClass(yogaClass);
        classManager.createClass(zumbaClass);
        
        classManager.registerMemberToClass("M001", "C001");
        classManager.registerMemberToClass("M002", "C002");
        
        Equipment treadmill = new Equipment("E001", "Treadmill X1", EquipmentType.CARDIO, LocalDate.now().minusMonths(3));
        Equipment benchPress = new Equipment("E002", "Bench Press Pro", EquipmentType.STRENGTH, LocalDate.now().minusMonths(6));
        
        equipmentManager.addEquipment(treadmill);
        equipmentManager.addEquipment(benchPress);
        
        paymentManager.processMembershipPayment("M001", 500000);
        paymentManager.processMembershipPayment("M002", 300000);
    }
    
    public void displayMemberReport() {
        System.out.println("\n===== MEMBER REPORT =====");
        List<Member> members = memberManager.getAllMembers();
        for (Member member : members) {
            System.out.println(member);
            System.out.println("Payment History:");
            List<Payment> payments = paymentManager.getPaymentHistory(member.getMemberId());
            for (Payment payment : payments) {
                System.out.println("  " + payment);
            }
            System.out.println();
        }
    }
    
    public void displayClassReport() {
        System.out.println("\n===== CLASS REPORT =====");
        List<FitnessClass> classes = classManager.getAvailableClasses();
        for (FitnessClass fitnessClass : classes) {
            System.out.println(fitnessClass);
            System.out.println("Enrolled Members:");
            List<Member> members = classManager.getMembersInClass(fitnessClass.getClassId());
            for (Member member : members) {
                System.out.println("  " + member.getName() + " (ID: " + member.getMemberId() + ")");
            }
            System.out.println();
        }
    }
    
    public void displayEquipmentReport() {
        System.out.println("\n===== EQUIPMENT REPORT =====");
        List<Equipment> equipments = equipmentManager.getAvailableEquipments();
        for (Equipment equipment : equipments) {
            System.out.println(equipment);
        }
        System.out.println();
    }
}