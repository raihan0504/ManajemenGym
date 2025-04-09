
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implementasi manajemen kelas fitness
class ClassManager implements IClassManagement {
    private Map<String, FitnessClass> classes;
    private IMemberManagement memberManager;
    private ILogger logger;
    private INotification notifier;
    
    public ClassManager(IMemberManagement memberManager, ILogger logger, INotification notifier) {
        this.classes = new HashMap<>();
        this.memberManager = memberManager;
        this.logger = logger;
        this.notifier = notifier;
    }
    
    @Override
    public boolean createClass(FitnessClass fitnessClass) {
        if (fitnessClass == null || classes.containsKey(fitnessClass.getClassId())) {
            return false;
        }
        
        classes.put(fitnessClass.getClassId(), fitnessClass);
        logger.logInfo("Class created: " + fitnessClass.getClassId());
        
        // Notify all members about new class
        notifier.sendBroadcastNotification("New class available: " + fitnessClass.getClassName());
        return true;
    }
    
    @Override
    public boolean cancelClass(String classId) {
        if (classId == null || !classes.containsKey(classId)) {
            return false;
        }
        
        FitnessClass classToCancel = classes.get(classId);
        
        // Notify enrolled members about cancellation
        for (String memberId : classToCancel.getEnrolledMembers()) {
            notifier.sendNotification(memberId, "Class cancelled: " + classToCancel.getClassName());
        }
        
        classes.remove(classId);
        logger.logInfo("Class cancelled: " + classId);
        return true;
    }
    
    @Override
    public boolean registerMemberToClass(String memberId, String classId) {
        Member member = memberManager.getMemberById(memberId);
        FitnessClass fitnessClass = classes.get(classId);
        
        if (member == null || fitnessClass == null) {
            return false;
        }
        
        if (fitnessClass.addMember(memberId)) {
            notifier.sendNotification(memberId, "You are enrolled in class: " + fitnessClass.getClassName());
            logger.logInfo("Member " + memberId + " enrolled in class " + classId);
            return true;
        }
        
        return false;
    }
    
    @Override
    public List<FitnessClass> getAvailableClasses() {
        return new ArrayList<>(classes.values());
    }
    
    @Override
    public List<Member> getMembersInClass(String classId) {
        FitnessClass fitnessClass = classes.get(classId);
        if (fitnessClass == null) {
            return new ArrayList<>();
        }
        
        List<Member> membersInClass = new ArrayList<>();
        for (String memberId : fitnessClass.getEnrolledMembers()) {
            Member member = memberManager.getMemberById(memberId);
            if (member != null) {
                membersInClass.add(member);
            }
        }
        
        return membersInClass;
    }
}