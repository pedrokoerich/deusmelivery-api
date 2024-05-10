package br.deusmelivery.deusmelivery.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.deusmelivery.deusmelivery.products.entity.Product;
import br.deusmelivery.deusmelivery.products.repository.ProductRepository;
import br.deusmelivery.deusmelivery.products.service.ProductService;

public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
       return productRepository.findById(id).get();
    }
    
}
