// Implementasi IUserRepository
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements IUserRepository {
    private Map<Integer, User> users = new HashMap<>();
    
    @Override
    public boolean save(User user) {
        users.put(user.getUserId(), user);
        return true;
    }
    
    @Override
    public boolean delete(int userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return true;
        }
        return false;
    }
    
    @Override
    public User findById(int userId) {
        return users.get(userId);
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
