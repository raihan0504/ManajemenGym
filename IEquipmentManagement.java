
import java.util.List;

// Interface untuk manajemen peralatan gym
interface IEquipmentManagement {
    boolean addEquipment(Equipment equipment);
    boolean removeEquipment(String equipmentId);
    boolean markEquipmentAsMaintenance(String equipmentId);
    List<Equipment> getAvailableEquipments();
}