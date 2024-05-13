package br.deusmelivery.deusmelivery.products.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int quantity;
    private float productValue;
    private String fornec;

    public Products() {
        // Construtor vazio necessário para JPA
    }


    public Products(Long id, String name, String category, int quantity, float productValue, String fornec) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.productValue = productValue;
        this.fornec = fornec;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public float getValue() {
        return productValue;
    }


    public void setValue(float productValue) {
        this.productValue = productValue;
    }


    public String getFornec() {
        return fornec;
    }


    public void setFornec(String fornec) {
        this.fornec = fornec;
    }

    
}
