package br.deusmelivery.deusmelivery.products.repository;

import org.springframework.stereotype.Repository;
import br.deusmelivery.deusmelivery.products.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{
        
    
} 