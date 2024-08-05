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
import br.deusmelivery.deusmelivery.products.entity.DTO.ProductDTO;
import br.deusmelivery.deusmelivery.products.repository.ProductsRepository;
import br.deusmelivery.deusmelivery.products.service.ProductsService;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import br.deusmelivery.deusmelivery.suppliers.repository.SuppliersRepository;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProductsServiceImpl implements ProductsService{
    
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

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
    public List<ProductDTO> listProducts(Map<String, String> filters) {
        List<Products> products = productsRepository.findAll(Specification.where(applyFilters(filters)));
        return products.stream().map(this::mapToProductDTO).collect(Collectors.toList());
    }

    private ProductDTO mapToProductDTO(Products product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setQuantity(product.getQuantity());
        dto.setProductValue(product.getProductValue());

        // Buscar o fornecedor pelo código armazenado em fornec
        Suppliers fornecedor = suppliersRepository.findById(product.getFornec()).orElse(null);
        if (fornecedor != null) {
            dto.setFornecedorName(fornecedor.getName());
        } else {
            dto.setFornecedorName("Fornecedor Desconhecido");
        }

        return dto;
    }

    private Specification<Products> applyFilters(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    switch (key) {
                        case "categoryFilter":
                            predicates.add(criteriaBuilder.equal(root.get("category"), value));
                            break;
                        // Adicione outros filtros conforme necessário
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
