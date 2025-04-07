

// Interface Provided untuk layanan pengguna
public interface IUserService {
    boolean registerUser(User user);
    boolean removeUser(int userId);
    User findUserById(int userId);
    List<User> getAllUsers();
}
