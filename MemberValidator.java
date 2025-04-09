// Implementasi sederhana untuk validasi
class MemberValidator implements IValidation {
    @Override
    public boolean validateMember(Member member) {
        // Validasi dasar
        if (member.getName() == null || member.getName().trim().isEmpty()) {
            return false;
        }
        
        if (member.getEmail() == null || !member.getEmail().contains("@")) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean validateClass(FitnessClass fitnessClass) {
        // Validasi dasar
        if (fitnessClass.getClassName() == null || fitnessClass.getClassName().trim().isEmpty()) {
            return false;
        }
        
        if (fitnessClass.getCapacity() <= 0) {
            return false;
        }
        
        return true;
    }
}