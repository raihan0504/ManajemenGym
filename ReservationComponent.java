import java.util.HashMap;
import java.util.Map;

class ReservationComponent implements IReservationService {
    private IRoomManagement roomService;
    private IPaymentProcessor paymentService;
    private INotificationService notificationService;
    
    private Map<String, Reservation> reservations = new HashMap<>();
    private RoomManagementComponent roomManagement; // Perlu akses langsung untuk harga
    
    public ReservationComponent(IRoomManagement roomService, 
                               IPaymentProcessor paymentService,
                               INotificationService notificationService) {
        this.roomService = roomService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        
        // Untuk akses ke metode tambahan yang tidak ada di interface
        if (roomService instanceof RoomManagementComponent) {
            this.roomManagement = (RoomManagementComponent) roomService;
        }
    }
    
    @Override
    public String createReservation(Reservation reservation) {
        // Cek ketersediaan kamar
        if (!roomService.checkRoomAvailability(
                reservation.getRoomType(), 
                reservation.getCheckInDate(), 
                reservation.getCheckOutDate())) {
            return null; // Tidak ada kamar yang tersedia
        }
        
        // Hitung total harga
        if (roomManagement != null) {
            double totalPrice = roomManagement.calculateTotalPrice(
                reservation.getRoomType(),
                reservation.getCheckInDate(), 
                reservation.getCheckOutDate());
            reservation.setTotalPrice(totalPrice);
        }
        
        // Buat ID reservasi
        String reservationId = generateReservationId();
        reservation.setReservationId(reservationId);
        
        // Simpan reservasi
        reservations.put(reservationId, reservation);
        
        // Book kamar
        roomService.bookRoom(
            reservation.getRoomType(), 
            reservation.getCheckInDate(), 
            reservation.getCheckOutDate(), 
            reservation.getGuestName());
        
        return reservationId;
    }
    
    public boolean processPayment(String reservationId, String paymentDetails) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            return false;
        }
        
        boolean paymentSuccess = paymentService.processPayment(
                reservation.getTotalPrice(), paymentDetails);
        
        if (paymentSuccess) {
            reservation.setPaid(true);
            notificationService.sendConfirmation(reservation);
        }
        
        return paymentSuccess;
    }
    
    @Override
    public Reservation getReservation(String reservationId) {
        return reservations.get(reservationId);
    }
    
    @Override
    public boolean cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            return false;
        }
        
        // Jika sudah dibayar, lakukan refund
        if (reservation.isPaid()) {
            paymentService.refundPayment(reservationId);
        }
        
        // Hapus dari daftar reservasi
        reservations.remove(reservationId);
        
        // Kirim notifikasi pembatalan
        notificationService.sendCancellationNotice(reservationId, reservation.getGuestEmail());
        
        return true;
    }
    
    private String generateReservationId() {
        return "RES-" + System.currentTimeMillis();
    }
}