package br.deusmelivery.deusmelivery.sales_orders.service;

import java.util.List;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.MonthlySalesDTO;
/* import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.MonthlySalesDTO; */
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.ProductsSalesDTO;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.SalesOrderDTO;
import br.deusmelivery.deusmelivery.users.entity.Users;

public interface SalesOrderService {
    boolean postSalesOrder(SalesOrder salesOrder);
    SalesOrder updateSalesOrder(Long id, SalesOrder salesOrder);
    boolean deleteSalesOrder(Long id);
    List<SalesOrder> getAllSalesOrder();
    SalesOrder getSalesOrderById(Long id);
    List<SalesOrderDTO> getAllSalesOrderWithNames();
    List<ProductsSalesDTO> getTop5BebidasMaisConsumidas();
    List<ProductsSalesDTO> getTop5EstadosMaisConsumidores();
    List<MonthlySalesDTO> getVendasNosUltimos12Meses();
    int getOrdersCountForCurrentYear();
    int getOrdersCountForCurrentMonth();
    List<Users> getTop10ClientesFieis();

}