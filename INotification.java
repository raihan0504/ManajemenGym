// Interface yang dibutuhkan untuk notifikasi
interface INotification {
    void sendNotification(String memberId, String message);
    void sendBroadcastNotification(String message);
}