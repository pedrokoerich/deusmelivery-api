package br.deusmelivery.deusmelivery.users.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.deusmelivery.deusmelivery.users.entity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
