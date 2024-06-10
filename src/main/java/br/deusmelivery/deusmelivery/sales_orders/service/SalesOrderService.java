package br.deusmelivery.deusmelivery.sales_orders.service;

import java.util.List;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;

public interface SalesOrderService {
    List<SalesOrder> getAllSalesOrder();
    SalesOrder getSalesOrderById(String id);
    boolean postSalesOrder(SalesOrder user);
    SalesOrder updateSalesOrder(String id, SalesOrder user);
    boolean deleteSalesOrder(String id);
}