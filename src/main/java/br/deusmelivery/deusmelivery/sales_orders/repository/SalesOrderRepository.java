package br.deusmelivery.deusmelivery.sales_orders.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>{
    List<SalesOrder> findAllByDataEntregaBetween(Date startDate, Date endDate);

}
