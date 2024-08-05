package br.deusmelivery.deusmelivery.sales_orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.*;

import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.service.SalesOrderService;
import br.deusmelivery.deusmelivery.users.entity.Users;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/sales_orders")
public class SalesOrderController {
    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @GetMapping
    public ResponseEntity<List<SalesOrderDTO>> getAllSalesOrder() {
        List<SalesOrderDTO> salesOrders = salesOrderService.getAllSalesOrderWithNames();
        return ResponseEntity.ok(salesOrders);
    }

    @GetMapping("/{id}")
    public SalesOrder getSalesOrderById(@PathVariable Long id) {
        return salesOrderService.getSalesOrderById(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> postSalesOrder(@RequestBody SalesOrder salesOrder) {
        boolean created = salesOrderService.postSalesOrder(salesOrder);
        if (created) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi criado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na criação do usuário
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesOrder> updateSalesOrder(@PathVariable Long id, @RequestBody SalesOrder salesOrder) {
        salesOrderService.updateSalesOrder(id, salesOrder);
        return new ResponseEntity<SalesOrder>(salesOrder, HttpStatus.OK);
    }

        
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSalesOrder(@PathVariable Long id) {
        boolean deleted = salesOrderService.deleteSalesOrder(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi excluido com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na exclusão do usuário
        }
    }

    @GetMapping("/Top5BebidasMaisConsumidas")
    public ResponseEntity<List<ProductsSalesDTO>> getTop5BebidasMaisConsumidas() {
        List<ProductsSalesDTO> topProducts = salesOrderService.getTop5BebidasMaisConsumidas();
        return ResponseEntity.ok(topProducts);
    }

    @GetMapping("/Top5EstadosMaisConsumidores")
    public ResponseEntity<List<ProductsSalesDTO>> getTop5EstadosMaisConsumidores() {
        List<ProductsSalesDTO> topEstados = salesOrderService.getTop5EstadosMaisConsumidores();
        return ResponseEntity.ok(topEstados);
    }

    @GetMapping("/VendasNosUltimos12Meses")
    public ResponseEntity<List<MonthlySalesDTO>> getVendasNosUltimos12Meses() {
        List<MonthlySalesDTO> topProducts = salesOrderService.getVendasNosUltimos12Meses();
        return ResponseEntity.ok(topProducts);
    }

    @GetMapping("/VendasNoAno")
    public int getOrdersCountForCurrentYear() {
        return salesOrderService.getOrdersCountForCurrentYear();
    }

    @GetMapping("/VendasNoMes")
    public int getOrdersCountForCurrentMonth() {
        return salesOrderService.getOrdersCountForCurrentMonth();
    }

    @GetMapping("/Top10ClientesFieis")
    public ResponseEntity<List<Users>> getTop10ClientesFieis() {
        List<Users> topProducts = salesOrderService.getTop10ClientesFieis();
        return ResponseEntity.ok(topProducts);
    }


}
