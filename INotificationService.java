// File: INotificationService.java
// Interface Provided untuk layanan notifikasi
public interface INotificationService {
    void sendBorrowNotification(User user, Book book);
    void sendReturnNotification(User user, Book book);
    void sendOverdueNotification(User user, Book book);
}

