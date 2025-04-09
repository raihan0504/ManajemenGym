
import java.time.LocalDateTime;

// Kelas untuk data pembayaran
class Payment {
    private String paymentId;
    private String memberId;
    private double amount;
    private LocalDateTime paymentDate;
    private PaymentType paymentType;
    
    // Constructor
    public Payment(String paymentId, String memberId, double amount, PaymentType paymentType) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
        this.paymentType = paymentType;
    }
    
    // Getters
    public String getPaymentId() { return paymentId; }
    public String getMemberId() { return memberId; }
    public double getAmount() { return amount; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public PaymentType getPaymentType() { return paymentType; }
    
    @Override
    public String toString() {
        return "Payment{id=" + paymentId + ", memberId=" + memberId + ", amount=" + amount + 
               ", date=" + paymentDate + ", type=" + paymentType + "}";
    }
}