package br.deusmelivery.deusmelivery.users.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;
import br.deusmelivery.deusmelivery.users.service.UsersService;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean createUser(Users user) {
        try {
            usersRepository.save(user);
            return true; // Retorna true se a criação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na criação
        }
    }

    @Override
    public Users updateUser(Long id, Users user) {
        if (usersRepository.existsById(id)) {
            user.setId(id); // Garante que o ID passado é o mesmo que o ID do usuário
            return usersRepository.save(user);
        } else {
            return null; // ou lançar uma exceção indicando que o usuário não foi encontrado
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            usersRepository.deleteById(id);
            return true; // Retorna true se a exclusão for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na exclusão
        }
    }


    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id).get();
    }

}
