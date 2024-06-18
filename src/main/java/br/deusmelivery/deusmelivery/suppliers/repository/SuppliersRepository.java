package br.deusmelivery.deusmelivery.suppliers.repository;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, String>{
    Optional<Suppliers> findById(String id);
    
}