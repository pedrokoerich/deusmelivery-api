package br.deusmelivery.deusmelivery.products.entity.DTO;

public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private int quantity;
    private Float productValue;
    private String fornecedorName; // Nome do fornecedor
    
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
    public Float getProductValue() {
        return productValue;
    }
    public void setProductValue(Float productValue) {
        this.productValue = productValue;
    }
    public String getFornecedorName() {
        return fornecedorName;
    }
    public void setFornecedorName(String fornecedorName) {
        this.fornecedorName = fornecedorName;
    }

    
}
