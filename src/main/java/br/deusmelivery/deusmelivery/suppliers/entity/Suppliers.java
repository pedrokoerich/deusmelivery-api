package br.deusmelivery.deusmelivery.suppliers.entity;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String fantasyName;
    private String status;
    private String cnpj;
    private String phone;
    private String email;
    @CreationTimestamp
    private Date inclusionDate;
    @UpdateTimestamp
    private Date updatedDate;
    private String state;
    private String city;
    private String address;
    private Number addressNumber;

}
