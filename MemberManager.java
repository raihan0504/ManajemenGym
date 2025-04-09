
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implementasi manajemen anggota
class MemberManager implements IMemberManagement {
    private Map<String, Member> members;
    private ILogger logger;
    private IValidation validator;
    
    public MemberManager(ILogger logger, IValidation validator) {
        this.members = new HashMap<>();
        this.logger = logger;
        this.validator = validator;
    }
    
    @Override
    public boolean registerMember(Member member) {
        if (member == null || members.containsKey(member.getMemberId())) {
            return false;
        }
        
        if (!validator.validateMember(member)) {
            logger.logError("Invalid member data", null);
            return false;
        }
        
        members.put(member.getMemberId(), member);
        logger.logInfo("Member registered: " + member.getMemberId());
        return true;
    }
    
    @Override
    public boolean removeMember(String memberId) {
        if (memberId == null || !members.containsKey(memberId)) {
            return false;
        }
        
        members.remove(memberId);
        logger.logInfo("Member removed: " + memberId);
        return true;
    }
    
    @Override
    public Member getMemberById(String memberId) {
        return members.get(memberId);
    }
    
    @Override
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }
}