
import java.time.LocalDate;

// Interface Required oleh Reservasi
interface IRoomManagement {
    boolean checkRoomAvailability(String roomType, LocalDate checkIn, LocalDate checkOut);
    boolean bookRoom(String roomType, LocalDate checkIn, LocalDate checkOut, String guestName);
}
