package br.deusmelivery.deusmelivery.users.service;

import java.util.List;
import br.deusmelivery.deusmelivery.users.entity.Users;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserById(String id);
    boolean createUser(Users user);
    Users updateUser(String id, Users user);
    boolean deleteUser(String id);
}
