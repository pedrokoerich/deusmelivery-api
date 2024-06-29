package br.deusmelivery.deusmelivery.products.service;

import java.util.List;
import java.util.Map;

import br.deusmelivery.deusmelivery.products.entity.Products;
import br.deusmelivery.deusmelivery.products.entity.DTO.CategoryProductQuantityDTO;

public interface ProductsService {
    boolean createProduct(Products product);
    Products updateProduct(Long id, Products product);
    boolean deleteProduct(Long id);
    List<Products> listProducts(Map<String, String> filters);
    List<Products> listProducts();
    Products getProductById(Long id);
    List<CategoryProductQuantityDTO> sumQuantityByCategory();

}
