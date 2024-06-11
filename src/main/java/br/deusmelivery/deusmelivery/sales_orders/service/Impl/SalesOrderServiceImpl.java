package br.deusmelivery.deusmelivery.sales_orders.service.Impl;

import org.springframework.stereotype.Service;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.repository.SalesOrderRepository;
import br.deusmelivery.deusmelivery.sales_orders.service.SalesOrderService;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }
    
    @Override
    public List<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAll();
    }

    @Override
    public SalesOrder getSalesOrderById(String id) {
        Optional<SalesOrder> salesOrderOptional = salesOrderRepository.findById(id);
        return salesOrderOptional.orElse(null);
    }

    @Override
    public boolean postSalesOrder(SalesOrder salesOrder) {
        try {
            salesOrderRepository.save(salesOrder);
            return true; // Retorna true se a criação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na criação
        }
    }

    @Override
    public SalesOrder updateSalesOrder(String id, SalesOrder salesOrder) {
        if (salesOrderRepository.existsById(id)) {
            salesOrder.setId(id); // Garante que o ID passado é o mesmo que o ID do pedido
            return salesOrderRepository.save(salesOrder);
        } else {
            return null; // ou lançar uma exceção indicando que o pedido não foi encontrado
        }
    }

    @Override
    public boolean deleteSalesOrder(String id) {
        try {
            salesOrderRepository.deleteById(id);
            return true; // Retorna true se a exclusão for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na exclusão
        }
    }

    
}
