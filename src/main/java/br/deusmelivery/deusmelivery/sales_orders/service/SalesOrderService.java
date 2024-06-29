package br.deusmelivery.deusmelivery.sales_orders.service;

import java.util.List;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.SalesOrderDTO;

public interface SalesOrderService {
boolean postSalesOrder(SalesOrder salesOrder);
    SalesOrder updateSalesOrder(Long id, SalesOrder salesOrder);
    boolean deleteSalesOrder(Long id);
    List<SalesOrder> getAllSalesOrder();
    SalesOrder getSalesOrderById(Long id);
    List<SalesOrderDTO> getAllSalesOrderWithNames();
}