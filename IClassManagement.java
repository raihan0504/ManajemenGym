
import java.util.List;

// Interface untuk manajemen kelas fitness
interface IClassManagement {
    boolean createClass(FitnessClass fitnessClass);
    boolean cancelClass(String classId);
    boolean registerMemberToClass(String memberId, String classId);
    List<FitnessClass> getAvailableClasses();
    List<Member> getMembersInClass(String classId);
}