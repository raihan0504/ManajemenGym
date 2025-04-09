// Model data
class Reservation {
    private String reservationId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private double totalPrice;
    private boolean isPaid;
    
    public Reservation(String guestName, String guestEmail, String guestPhone, 
                      String roomType, LocalDate checkInDate, LocalDate checkOutDate, 
                      int numberOfGuests) {
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.isPaid = false;
        // ID akan dibuat saat reservasi disimpan
    }
    
    // Getters dan Setters
    public String getReservationId() {
        return reservationId;
    }
    
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    
    public String getGuestName() {
        return guestName;
    }
    
    public String getGuestEmail() {
        return guestEmail;
    }
    
    public String getGuestPhone() {
        return guestPhone;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    
    public int getNumberOfGuests() {
        return numberOfGuests;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public boolean isPaid() {
        return isPaid;
    }
    
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", checkIn=" + checkInDate +
                ", checkOut=" + checkOutDate +
                ", guests=" + numberOfGuests +
                ", totalPrice=" + totalPrice +
                ", paid=" + isPaid +
                '}';
    }
}