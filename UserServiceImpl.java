
// Implementasi IUserService
import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;
    
    // Constructor dependency injection
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean registerUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public boolean removeUser(int userId) {
        return userRepository.delete(userId);
    }
    
    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
