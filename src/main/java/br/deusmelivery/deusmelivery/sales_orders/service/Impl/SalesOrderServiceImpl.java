package br.deusmelivery.deusmelivery.sales_orders.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.deusmelivery.deusmelivery.products.repository.ProductsRepository;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.SalesOrderDTO;
import br.deusmelivery.deusmelivery.sales_orders.repository.SalesOrderRepository;
import br.deusmelivery.deusmelivery.sales_orders.service.SalesOrderService;
import br.deusmelivery.deusmelivery.suppliers.repository.SuppliersRepository;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
    @Autowired
    private final SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }
    
    @Override
    public List<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAll();
    }

    @Override
    public SalesOrder getSalesOrderById(Long id) {
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
    public SalesOrder updateSalesOrder(Long id, SalesOrder salesOrder) {
        if (salesOrderRepository.existsById(id)) {
            salesOrder.setId(id); // Garante que o ID passado é o mesmo que o ID do pedido
            return salesOrderRepository.save(salesOrder);
        } else {
            return null; // ou lançar uma exceção indicando que o pedido não foi encontrado
        }
    }

    @Override
    public boolean deleteSalesOrder(Long id) {
        try {
            salesOrderRepository.deleteById(id);
            return true; // Retorna true se a exclusão for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na exclusão
        }
    }

    @Override
    public List<SalesOrderDTO> getAllSalesOrderWithNames() {
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        List<SalesOrderDTO> salesOrderDTOs = new ArrayList<>();
        for (SalesOrder salesOrder : salesOrders) {
            SalesOrderDTO dto = new SalesOrderDTO();
            dto.setId(salesOrder.getId());
            dto.setProductName((productsRepository.findById(Long.parseLong(salesOrder.getProduto()))).get().getName());
            dto.setUserName((usersRepository.findById(Long.parseLong(salesOrder.getCliente()))).get().getName());
            dto.setSupplierName((suppliersRepository.findById(Long.parseLong(salesOrder.getFornecedor())).get().getFantasyName()));
            dto.setQuantity(salesOrder.getQuantidade());
            dto.setPrice(salesOrder.getValorUnitario());
            dto.setTotalPrice(salesOrder.getValorTotal());
            dto.setDataEntrega(salesOrder.getDataEntrega());
            dto.setHoraEntrega(salesOrder.getHoraEntrega());
            salesOrderDTOs.add(dto);
        }

       /*  return salesOrders.stream().map(salesOrder -> {
            SalesOrderDTO dto = new SalesOrderDTO();
            dto.setId(salesOrder.getId());
            dto.setProductName((productsRepository.findByNameEquals(salesOrder.getProduto())).getName());
            dto.setUserName((usersRepository.findByNameEquals(salesOrder.getCliente())).getName());
            dto.setSupplierName((suppliersRepository.findByNameEquals(salesOrder.getFornecedor())).getFantasyName());
            dto.setQuantity(salesOrder.getQuantidade());
            dto.setTotalPrice(salesOrder.getValorTotal());
            return dto;
        }).collect(Collectors.toList()); */

        return salesOrderDTOs;
    }

    
}
