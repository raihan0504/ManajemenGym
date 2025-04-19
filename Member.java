class Member {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String membershipType;
    private String expiryDate;

    public Member(int id, String name, String email, String phone, String membershipType, String expiryDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipType = membershipType;
        this.expiryDate = expiryDate;
    }

    // Getter dan setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString() {
        return "ID: " + id + 
               "\nNama: " + name + 
               "\nEmail: " + email + 
               "\nTelepon: " + phone + 
               "\nTipe Keanggotaan: " + membershipType + 
               "\nTanggal Kadaluarsa: " + expiryDate;
    }
}