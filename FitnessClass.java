import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FitnessClass {
    private String classId;
    private String className;
    private String trainer;
    private LocalDateTime schedule;
    private int capacity;
    private List<String> enrolledMembers;
    
    // Constructor
    public FitnessClass(String classId, String className, String trainer, 
                       LocalDateTime schedule, int capacity) {
        this.classId = classId;
        this.className = className;
        this.trainer = trainer;
        this.schedule = schedule;
        this.capacity = capacity;
        this.enrolledMembers = new ArrayList<>();
    }
    
    // Getters dan setters
    public String getClassId() { return classId; }
    public String getClassName() { return className; }
    public String getTrainer() { return trainer; }
    public LocalDateTime getSchedule() { return schedule; }
    public int getCapacity() { return capacity; }
    public List<String> getEnrolledMembers() { return enrolledMembers; }
    
    public boolean addMember(String memberId) {
        if (enrolledMembers.size() < capacity) {
            enrolledMembers.add(memberId);
            return true;
        }
        return false;
    }
    
    public boolean removeMember(String memberId) {
        return enrolledMembers.remove(memberId);
    }
    
    @Override
    public String toString() {
        return "FitnessClass{id=" + classId + ", name=" + className + ", trainer=" + trainer + 
               ", schedule=" + schedule + ", enrolled=" + enrolledMembers.size() + "/" + capacity + "}";
    }
}