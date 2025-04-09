// Enum untuk tipe keanggotaan
enum MembershipType {
    BASIC(1), 
    STANDARD(3), 
    PREMIUM(6), 
    VIP(12);
    
    private int durationMonths;
    
    MembershipType(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    
    public int getDurationMonths() {
        return durationMonths;
    }
}