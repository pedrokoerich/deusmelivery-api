package br.deusmelivery.deusmelivery.sales_orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.service.SalesOrderService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/sales_orders")
public class SalesOrderController {
    @Autowired
    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @GetMapping
    public List<SalesOrder> getAllSalesOrder() {
        return salesOrderService.getAllSalesOrder();
    }

    @GetMapping("/{id}")
    public SalesOrder getSalesOrderById(@PathVariable String id) {
        return salesOrderService.getSalesOrderById(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> createUser(@RequestBody SalesOrder salesOrder) {
        boolean created = salesOrderService.postSalesOrder(salesOrder);
        if (created) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi criado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na criação do usuário
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesOrder> updateUser(@PathVariable String id, @RequestBody SalesOrder salesOrder) {
        salesOrderService.updateSalesOrder(id, salesOrder);
        return new ResponseEntity<SalesOrder>(salesOrder, HttpStatus.OK);
    }

        
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSalesOrder(@PathVariable String id) {
        boolean deleted = salesOrderService.deleteSalesOrder(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi excluido com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na exclusão do usuário
        }
    }

}
