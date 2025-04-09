
import java.util.List;

// Implementasi sederhana untuk notifikasi
class EmailNotificationService implements INotification {
    private IMemberManagement memberManager;
    
    public EmailNotificationService(IMemberManagement memberManager) {
        this.memberManager = memberManager;
    }
    
    @Override
    public void sendNotification(String memberId, String message) {
        Member member = memberManager.getMemberById(memberId);
        if (member != null) {
            System.out.println("Sending email to " + member.getEmail() + ": " + message);
            // Di implementasi nyata, ini akan mengirim email
        }
    }
    
    @Override
    public void sendBroadcastNotification(String message) {
        List<Member> allMembers = memberManager.getAllMembers();
        for (Member member : allMembers) {
            sendNotification(member.getMemberId(), message);
        }
    }
}