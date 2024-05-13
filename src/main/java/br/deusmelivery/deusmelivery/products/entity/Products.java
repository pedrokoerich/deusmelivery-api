package br.deusmelivery.deusmelivery.products.entity;

import java.sql.Date;

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
    private Number quantity;
    private Number value;
    private String fornec;

    public Products() {
        // Construtor vazio necessário para JPA
    }


    public Products(Long id, String name, String category, Number quantity, Number value, String fornec) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.value = value;
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


    public Number getQuantity() {
        return quantity;
    }


    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }


    public Number getValue() {
        return value;
    }


    public void setValue(Number value) {
        this.value = value;
    }


    public String getFornec() {
        return fornec;
    }


    public void setFornec(String fornec) {
        this.fornec = fornec;
    }

    
}
