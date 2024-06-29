package br.deusmelivery.deusmelivery.products.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.deusmelivery.deusmelivery.products.entity.Products;
import br.deusmelivery.deusmelivery.products.entity.DTO.CategoryProductQuantityDTO;
import br.deusmelivery.deusmelivery.products.service.ProductsService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/indicadores")
    public ResponseEntity<List<CategoryProductQuantityDTO>> getSumQuantityByCategory() {
        List<CategoryProductQuantityDTO> sumQuantityByCategory = productsService.sumQuantityByCategory();
        return ResponseEntity.ok(sumQuantityByCategory);
    }
    

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(@RequestParam Map<String, String> filters) {
        List<Products> products = productsService.listProducts(filters);
        return ResponseEntity.ok(products);
    }
    
    

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        return productsService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> createProduct(@RequestBody Products products) {
        boolean created = productsService.createProduct(products);
        if (created) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi criado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na criação do usuário
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products products) {
        productsService.updateProduct(id, products);
        return new ResponseEntity<Products>(products, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        boolean deleted = productsService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi excluido com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na exclusão do usuário
        }
    }



}
