public interface IPaymentProcessor {
    boolean processPayment(double amount, String paymentDetails);
    boolean refundPayment(String reservationId);
}
