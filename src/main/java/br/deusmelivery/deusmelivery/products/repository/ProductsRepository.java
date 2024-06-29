package br.deusmelivery.deusmelivery.products.repository;

import org.springframework.stereotype.Repository;
import br.deusmelivery.deusmelivery.products.entity.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor<Products> {
    Optional<Products> findById(Long id);
    Products findByNameEquals(String name);
    List<Products> findTop10ByOrderByNameAsc();
}