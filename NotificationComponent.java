// Komponen Notifikasi
class NotificationComponent implements INotificationService {
    @Override
    public void sendConfirmation(Reservation reservation) {
        // Simulasi pengiriman email konfirmasi
        System.out.println("--- KONFIRMASI RESERVASI ---");
        System.out.println("Kepada: " + reservation.getGuestName());
        System.out.println("Email: " + reservation.getGuestEmail());
        System.out.println("\nReservasi Anda telah dikonfirmasi!");
        System.out.println("ID Reservasi: " + reservation.getReservationId());
        System.out.println("Tipe Kamar: " + reservation.getRoomType());
        System.out.println("Check-in: " + reservation.getCheckInDate());
        System.out.println("Check-out: " + reservation.getCheckOutDate());
        System.out.println("Total Harga: Rp " + reservation.getTotalPrice());
        System.out.println("Status Pembayaran: " + (reservation.isPaid() ? "Lunas" : "Belum Dibayar"));
        System.out.println("------------------------");
    }
    
    @Override
    public void sendCancellationNotice(String reservationId, String guestEmail) {
        // Simulasi pengiriman email pembatalan
        System.out.println("--- NOTIFIKASI PEMBATALAN ---");
        System.out.println("Kepada: " + guestEmail);
        System.out.println("\nReservasi Anda dengan ID: " + reservationId + " telah dibatalkan.");
        System.out.println("------------------------");
    }
}
