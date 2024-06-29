package br.deusmelivery.deusmelivery.products.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.deusmelivery.deusmelivery.products.entity.Products;
import br.deusmelivery.deusmelivery.products.entity.DTO.CategoryProductQuantityDTO;
import br.deusmelivery.deusmelivery.products.repository.ProductsRepository;
import br.deusmelivery.deusmelivery.products.service.ProductsService;
import jakarta.persistence.criteria.Predicate;

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
    public List<Products> listProducts(Map<String, String> filters) {
        return productsRepository.findAll(Specification.where(applyFilters(filters)));
    }

    @Override
    public List<Products> listProducts() {
        return productsRepository.findAll();
    }
    
    private Specification<Products> applyFilters(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
    
            filters.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    switch (key) {
                        case "categoryFilter":
                            predicates.add(criteriaBuilder.equal(root.get("categoryFilter"), value));
                            break;
                        case "name":
                            predicates.add(criteriaBuilder.like(root.get("name"), "%" + value + "%"));
                            break;
                        case "quantity":
                            predicates.add(criteriaBuilder.equal(root.get("quantity"), Integer.parseInt(value)));
                            break;
                        case "productValue":
                            predicates.add(criteriaBuilder.equal(root.get("productValue"), Float.parseFloat(value)));
                            break;
                        case "fornec":
                            predicates.add(criteriaBuilder.equal(root.get("fornec"), value));
                            break;
                    }
                }
            });
    
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<CategoryProductQuantityDTO> sumQuantityByCategory() {
        return productsRepository.findAll().stream()
                .collect(Collectors.groupingBy(Products::getCategory, Collectors.summingLong(Products::getQuantity)))
                .entrySet().stream()
                .map(entry -> new CategoryProductQuantityDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


    @Override
    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }
    
}
