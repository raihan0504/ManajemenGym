public class Payment {
    private int id;
    private int memberId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String description;

    public Payment(int id, int memberId, double amount, String paymentDate, String paymentMethod, String description) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.description = description;
    }

    // Getter dan setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "ID: " + id + 
               "\nID Anggota: " + memberId + 
               "\nJumlah: " + amount + 
               "\nTanggal Pembayaran: " + paymentDate + 
               "\nMetode Pembayaran: " + paymentMethod + 
               "\nDeskripsi: " + description;
    }
}