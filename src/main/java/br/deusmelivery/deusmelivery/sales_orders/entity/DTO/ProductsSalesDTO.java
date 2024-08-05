package br.deusmelivery.deusmelivery.sales_orders.entity.DTO;

public class ProductsSalesDTO {
    private String productName;
    private String stateSale;
    private int quantitySold;
    private float percentageSold;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStateSale() {
        return stateSale;
    }

    public void setStateSale(String stateSale) {
        this.stateSale = stateSale;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public float getPercentageSold() {
        return percentageSold;
    }

    public void setPercentageSold(float percentageSold) {
        this.percentageSold = percentageSold;
    }

}
