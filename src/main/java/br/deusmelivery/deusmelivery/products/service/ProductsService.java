package br.deusmelivery.deusmelivery.products.service;

import java.util.List;


import br.deusmelivery.deusmelivery.products.entity.Products;

public interface ProductsService {
    boolean createProduct(Products product);
    Products updateProduct(Long id, Products product);
    boolean deleteProduct(Long id);
    List<Products> listProducts();
    Products getProductById(Long id);

}
