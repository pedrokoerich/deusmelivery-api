package br.deusmelivery.deusmelivery.products.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.deusmelivery.deusmelivery.products.entity.Products;
import br.deusmelivery.deusmelivery.products.repository.ProductsRepository;
import br.deusmelivery.deusmelivery.products.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{
    
    @Autowired
    private ProductsRepository productsRepository;
        public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    @Override
    public boolean createProduct(Products products) {
        try {
            productsRepository.save(products);
            return true; // Retorna true se a criação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na criação
        }
    }

    @Override
    public Products updateProduct(Long id, Products products) {
        if (productsRepository.existsById(id)) {
            products.setId(id); // Garante que o ID passado é o mesmo que o ID do usuário
            return productsRepository.save(products);
        } else {
            return null; // ou lançar uma exceção indicando que o usuário não foi encontrado
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        try {
            productsRepository.deleteById(id);
            return true; // Retorna true se a exclusão for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na exclusão
        }
    }

    @Override
    public List<Products> listProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(Long id) {
       return productsRepository.findById(id).get();
    }
    
}
