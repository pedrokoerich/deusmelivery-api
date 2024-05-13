package br.deusmelivery.deusmelivery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import br.deusmelivery.deusmelivery.products.service.ProductsService;
import br.deusmelivery.deusmelivery.products.entity.Products;

@SpringBootTest
@ContextConfiguration
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ProductServiceTests {

    @Autowired
    private ProductsService productService;

    @Test
    public void getAllProductsEmptyList() {
        var listProducts = productService.listProducts();
        assertEquals(listProducts.size(), 0);

    }
    @Test
    public void createProduct() {
        var product = new Products(null, "Product 1", "Description 1", 10.0, null, null);
        productService.createProduct(product);
    }   
    
}
