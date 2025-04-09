interface IReservationService {
    String createReservation(Reservation reservation);
    Reservation getReservation(String reservationId);
    boolean cancelReservation(String reservationId);
}