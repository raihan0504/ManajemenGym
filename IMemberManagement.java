
import java.util.List;

// Interface untuk manajemen anggota gym
interface IMemberManagement {
    boolean registerMember(Member member);
    boolean removeMember(String memberId);
    Member getMemberById(String memberId);
    List<Member> getAllMembers();
}