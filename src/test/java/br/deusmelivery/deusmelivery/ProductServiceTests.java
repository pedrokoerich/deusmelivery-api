package br.deusmelivery.deusmelivery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import br.deusmelivery.deusmelivery.products.service.ProductService;
import br.deusmelivery.deusmelivery.products.entity.Product;

@SpringBootTest
@ContextConfiguration
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void getAllProductsEmptyList() {
        var listProducts = productService.listProducts();
        assertEquals(listProducts.size(), 0);

    }
    @Test
    public void createProduct() {
        var product = new Product("Product 1", "Description 1", 10.0);
        productService.createProduct(product);
    }   
    
}
