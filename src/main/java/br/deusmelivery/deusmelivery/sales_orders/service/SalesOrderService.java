package br.deusmelivery.deusmelivery.sales_orders.service;

import java.util.List;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;

public interface SalesOrderService {
    List<SalesOrder> getAllSalesOrder();
    SalesOrder getSalesOrderById(Long id);
    boolean postSalesOrder(SalesOrder user);
    SalesOrder updateSalesOrder(Long id, SalesOrder user);
    boolean deleteSalesOrder(Long id);
}