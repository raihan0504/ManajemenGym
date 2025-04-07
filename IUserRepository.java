
// File: IUserRepository.java
// Interface Required untuk repositori pengguna

import java.util.List;

public interface IUserRepository {
    boolean save(User user);
    boolean delete(int userId);
    User findById(int userId);
    List<User> findAll();
}

