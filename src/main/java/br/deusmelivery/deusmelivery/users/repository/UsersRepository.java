package br.deusmelivery.deusmelivery.users.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.deusmelivery.deusmelivery.users.entity.Users;
import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findByLogin(String login);
}
