
import java.time.LocalDate;

// Kelas untuk data anggota gym
class Member {
    private String memberId;
    private String name;
    private String contactNumber;
    private String email;
    private MembershipType membershipType;
    private LocalDate registrationDate;
    private LocalDate expiryDate;
    
    // Constructor
    public Member(String memberId, String name, String contactNumber, String email, 
                 MembershipType membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.membershipType = membershipType;
        this.registrationDate = LocalDate.now();
        this.expiryDate = registrationDate.plusMonths(membershipType.getDurationMonths());
    }
    
    // Getters dan setters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public MembershipType getMembershipType() { return membershipType; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    
    public void renewMembership(int months) {
        this.expiryDate = this.expiryDate.plusMonths(months);
    }
    
    @Override
    public String toString() {
        return "Member{id=" + memberId + ", name=" + name + ", membershipType=" + membershipType + 
               ", expires=" + expiryDate + "}";
    }
}