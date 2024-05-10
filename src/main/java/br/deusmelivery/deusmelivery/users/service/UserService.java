package br.deusmelivery.deusmelivery.users.service;

import java.util.List;
import br.deusmelivery.deusmelivery.users.entity.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
