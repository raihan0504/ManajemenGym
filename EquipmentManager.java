
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Implementasi manajemen peralatan gym
class EquipmentManager implements IEquipmentManagement {
    private Map<String, Equipment> equipments;
    private ILogger logger;
    
    public EquipmentManager(ILogger logger) {
        this.equipments = new HashMap<>();
        this.logger = logger;
    }
    
    @Override
    public boolean addEquipment(Equipment equipment) {
        if (equipment == null || equipments.containsKey(equipment.getEquipmentId())) {
            return false;
        }
        
        equipments.put(equipment.getEquipmentId(), equipment);
        logger.logInfo("Equipment added: " + equipment.getEquipmentId());
        return true;
    }
    
    @Override
    public boolean removeEquipment(String equipmentId) {
        if (equipmentId == null || !equipments.containsKey(equipmentId)) {
            return false;
        }
        
        equipments.remove(equipmentId);
        logger.logInfo("Equipment removed: " + equipmentId);
        return true;
    }
    
    @Override
    public boolean markEquipmentAsMaintenance(String equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        if (equipment == null) {
            return false;
        }
        
        equipment.setUnderMaintenance(true);
        logger.logInfo("Equipment marked for maintenance: " + equipmentId);
        return true;
    }
    
    @Override
    public List<Equipment> getAvailableEquipments() {
        return equipments.values().stream()
            .filter(e -> !e.isUnderMaintenance())
            .collect(Collectors.toList());
    }
}