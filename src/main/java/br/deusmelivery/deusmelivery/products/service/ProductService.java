package br.deusmelivery.deusmelivery.products.service;

import java.util.List;
import br.deusmelivery.deusmelivery.products.entity.Product;

public interface ProductService {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> listProducts();
    Product getProductById(Long id);

}
