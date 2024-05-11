package br.deusmelivery.deusmelivery.users.service;

import java.util.List;
import br.deusmelivery.deusmelivery.users.entity.Users;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    boolean createUser(Users user);
    Users updateUser(Long id, Users user);
    void deleteUser(Long id);
}
