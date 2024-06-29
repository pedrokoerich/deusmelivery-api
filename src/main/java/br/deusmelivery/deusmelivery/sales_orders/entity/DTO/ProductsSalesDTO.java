package br.deusmelivery.deusmelivery.sales_orders.entity.DTO;

public class ProductsSalesDTO {
    private String productName;
    private int quantitySold;


    public ProductsSalesDTO(String productName, int quantitySold) {
        this.productName = productName;
        this.quantitySold = quantitySold;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
