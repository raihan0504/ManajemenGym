
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

// Implementasi pembayaran dan keuangan
class PaymentManager implements IPaymentManagement {
    private Map<String, Payment> payments;
    private IMemberManagement memberManager;
    private ILogger logger;
    private INotification notifier;
    
    public PaymentManager(IMemberManagement memberManager, ILogger logger, INotification notifier) {
        this.payments = new HashMap<>();
        this.memberManager = memberManager;
        this.logger = logger;
        this.notifier = notifier;
    }
    
    @Override
    public boolean processMembershipPayment(String memberId, double amount) {
        Member member = memberManager.getMemberById(memberId);
        if (member == null || amount <= 0) {
            return false;
        }
        
        String paymentId = "PMT-" + UUID.randomUUID().toString().substring(0, 8);
        Payment payment = new Payment(paymentId, memberId, amount, PaymentType.MEMBERSHIP_FEE);
        
        payments.put(paymentId, payment);
        logger.logInfo("Payment processed: " + paymentId + " for member " + memberId);
        
        notifier.sendNotification(memberId, "Payment of $" + amount + " processed successfully");
        return true;
    }
    
    @Override
    public boolean refundPayment(String paymentId) {
        Payment payment = payments.get(paymentId);
        if (payment == null) {
            return false;
        }
        
        // Proses refund
        logger.logInfo("Payment refunded: " + paymentId);
        notifier.sendNotification(payment.getMemberId(), "Refund processed for payment " + paymentId);
        return true;
    }
    
    @Override
    public List<Payment> getPaymentHistory(String memberId) {
        return payments.values().stream()
            .filter(p -> p.getMemberId().equals(memberId))
            .collect(Collectors.toList());
    }
}