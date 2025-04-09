class PaymentComponent implements IPaymentProcessor {
    private Map<String, PaymentRecord> paymentRecords = new HashMap<>();
    
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        // Simulasi proses pembayaran
        System.out.println("Memproses pembayaran sebesar " + amount);
        
        // Di sini bisa ditambahkan logika untuk integrasi dengan gateway pembayaran
        // Untuk contoh, kita hanya simulasikan keberhasilan pembayaran
        
        String transactionId = generateTransactionId();
        paymentRecords.put(transactionId, new PaymentRecord(transactionId, amount, paymentDetails));
        
        return true;
    }
    
    @Override
    public boolean refundPayment(String reservationId) {
        // Simulasi proses pengembalian dana
        System.out.println("Memproses pengembalian dana untuk reservasi: " + reservationId);
        return true;
    }
    
    private String generateTransactionId() {
        return "TRX-" + System.currentTimeMillis();
    }
    
    private class PaymentRecord {
        private String transactionId;
        private double amount;
        private String details;
        private LocalDateTime timestamp;
        
        public PaymentRecord(String transactionId, double amount, String details) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.details = details;
            this.timestamp = LocalDateTime.now();
        }
    }
}