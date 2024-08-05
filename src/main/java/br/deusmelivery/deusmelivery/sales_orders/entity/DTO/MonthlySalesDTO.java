package br.deusmelivery.deusmelivery.sales_orders.entity.DTO;

public class MonthlySalesDTO {
    private String month;
    private String category;
    private double totalSales;

    // Getters e Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}
