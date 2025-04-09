
import java.util.List;

// Komponen Manajemen Kamar
class RoomManagementComponent implements IRoomManagement {
    private Map<String, Double> roomPrices = new HashMap<>();
    private Map<String, List<Room>> availableRooms = new HashMap<>();
    
    public RoomManagementComponent() {
        initializeRooms();
    }
    
    private void initializeRooms() {
        // Inisialisasi harga kamar
        roomPrices.put("Standard", 500000.0);
        roomPrices.put("Deluxe", 850000.0);
        roomPrices.put("Suite", 1500000.0);
        
        // Inisialisasi kamar yang tersedia
        availableRooms.put("Standard", createRooms("Standard", 10));
        availableRooms.put("Deluxe", createRooms("Deluxe", 5));
        availableRooms.put("Suite", createRooms("Suite", 2));
    }
    
    private List<Room> createRooms(String type, int count) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            rooms.add(new Room(type + "-" + i, type));
        }
        return rooms;
    }
    
    @Override
    public boolean checkRoomAvailability(String roomType, LocalDate checkIn, LocalDate checkOut) {
        List<Room> rooms = availableRooms.get(roomType);
        if (rooms == null || rooms.isEmpty()) {
            return false;
        }
        
        // Cek apakah ada kamar yang tersedia untuk tanggal yang diminta
        for (Room room : rooms) {
            if (room.isAvailable(checkIn, checkOut)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean bookRoom(String roomType, LocalDate checkIn, LocalDate checkOut, String guestName) {
        List<Room> rooms = availableRooms.get(roomType);
        if (rooms == null || rooms.isEmpty()) {
            return false;
        }
        
        // Cari kamar yang tersedia dan booking
        for (Room room : rooms) {
            if (room.isAvailable(checkIn, checkOut)) {
                room.book(checkIn, checkOut, guestName);
                return true;
            }
        }
        
        return false;
    }
    
    public double getRoomPrice(String roomType) {
        return roomPrices.getOrDefault(roomType, 0.0);
    }
    
    public double calculateTotalPrice(String roomType, LocalDate checkIn, LocalDate checkOut) {
        double pricePerNight = getRoomPrice(roomType);
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return pricePerNight * nights;
    }
    
    // Kelas helper untuk representasi kamar
    private class Room {
        private String roomNumber;
        private String roomType;
        private Map<LocalDate, String> bookings = new HashMap<>();
        
        public Room(String roomNumber, String roomType) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
        }
        
        public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
            LocalDate date = checkIn;
            while (!date.isAfter(checkOut.minusDays(1))) {
                if (bookings.containsKey(date)) {
                    return false;
                }
                date = date.plusDays(1);
            }
            return true;
        }
        
        public void book(LocalDate checkIn, LocalDate checkOut, String guestName) {
            LocalDate date = checkIn;
            while (!date.isAfter(checkOut.minusDays(1))) {
                bookings.put(date, guestName);
                date = date.plusDays(1);
            }
        }
    }
}
