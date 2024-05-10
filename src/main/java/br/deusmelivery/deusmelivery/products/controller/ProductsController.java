package br.deusmelivery.deusmelivery.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.deusmelivery.deusmelivery.products.entity.Product;
import br.deusmelivery.deusmelivery.products.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product products = productService.getProductById(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("list")
    public ResponseEntity savw(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }
    

}
