package br.deusmelivery.deusmelivery.suppliers.repository;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long>{
    Optional<Suppliers> findById(Long id);
    List<Suppliers> findByNameContaining(String name);
    List<Suppliers> findTop10ByOrderByNameAsc();
    
}
