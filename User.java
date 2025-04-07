// Class untuk entitas Pengguna
public class User {
    private int userId;
    private String name;
    private String email;
    private String membershipType; // REGULAR, PREMIUM
    
    public User(int userId, String name, String email, String membershipType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
    }
    
    // Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMembershipType() {
        return membershipType;
    }
    
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}