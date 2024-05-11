package br.deusmelivery.deusmelivery.users.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;
import br.deusmelivery.deusmelivery.users.service.UsersService;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository userRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Long id, Users user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Garante que o ID passado é o mesmo que o ID do usuário
            return userRepository.save(user);
        } else {
            return null; // ou lançar uma exceção indicando que o usuário não foi encontrado
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
