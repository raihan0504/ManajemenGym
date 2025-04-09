import java.time.LocalDate;

class Equipment {
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private LocalDate purchaseDate;
    private boolean underMaintenance;
    
    // Constructor
    public Equipment(String equipmentId, String name, EquipmentType type, LocalDate purchaseDate) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
        this.purchaseDate = purchaseDate;
        this.underMaintenance = false;
    }
    
    // Getters dan setters
    public String getEquipmentId() { return equipmentId; }
    public String getName() { return name; }
    public EquipmentType getType() { return type; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public boolean isUnderMaintenance() { return underMaintenance; }
    
    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }
    
    @Override
    public String toString() {
        return "Equipment{id=" + equipmentId + ", name=" + name + ", type=" + type + 
               ", status=" + (underMaintenance ? "Under Maintenance" : "Available") + "}";
    }
}