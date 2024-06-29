package br.deusmelivery.deusmelivery.sales_orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>{

    
}
