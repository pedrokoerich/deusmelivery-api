package br.deusmelivery.deusmelivery.products.entity.DTO;

public class CategoryProductQuantityDTO {
    private String category;
    private long totalQuantity;

    public CategoryProductQuantityDTO(String category, long totalQuantity) {
        this.category = category;
        this.totalQuantity = totalQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
