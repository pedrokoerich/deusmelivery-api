package br.deusmelivery.deusmelivery.sales_orders.entity.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class SalesOrderDTO {
    private Long id;
    private String productName;
    private String userName;
    private String supplierName;
    private int quantity;
    private Float price;
    private Float totalPrice;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataEntrega;
    private String horaEntrega;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Date getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public String getHoraEntrega() {
        return horaEntrega;
    }
    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
    
    
}

