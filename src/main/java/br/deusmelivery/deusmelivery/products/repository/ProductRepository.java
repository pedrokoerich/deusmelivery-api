package br.deusmelivery.deusmelivery.products.repository;

import org.springframework.stereotype.Repository;
import br.deusmelivery.deusmelivery.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository 
    extends JpaRepository<Product, Long>{
        
    
} 