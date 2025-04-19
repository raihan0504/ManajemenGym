interface IPaymentProcess {
    boolean processPayment(Payment payment);
    void refundPayment(int paymentId);
    Payment getPaymentDetail(int paymentId);
}