import java.util.List;

// Interface untuk pembayaran dan keuangan
interface IPaymentManagement {
    boolean processMembershipPayment(String memberId, double amount);
    boolean refundPayment(String paymentId);
    List<Payment> getPaymentHistory(String memberId);
}