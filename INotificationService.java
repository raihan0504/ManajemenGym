// Interface Provided untuk Notifikasi
interface INotificationService {
    void sendConfirmation(Reservation reservation);
    void sendCancellationNotice(String reservationId, String guestEmail);
}