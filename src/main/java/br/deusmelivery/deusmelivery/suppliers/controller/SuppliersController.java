package br.deusmelivery.deusmelivery.suppliers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import br.deusmelivery.deusmelivery.suppliers.entity.DTO.SuppliersComboDTO;
import br.deusmelivery.deusmelivery.suppliers.service.SuppliersService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/suppliers")

public class SuppliersController {
    @Autowired
    private final SuppliersService suppliersService;

    public SuppliersController(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return suppliersService.getAllSuppliers();
    }

    @GetMapping("/combo")
    public List<SuppliersComboDTO> getComboSuppliers(@RequestParam(required = false) String filter) {
        return suppliersService.getComboSuppliers(filter);
    }

    @GetMapping("/{id}")
    public Suppliers getSuppliersById(@PathVariable String id) {
        return suppliersService.getSuppliersById(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> createSuppliers(@RequestBody Suppliers suppliers) {
        boolean created = suppliersService.postSuppliers(suppliers);
        if (created) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi criado com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na criação do usuário
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suppliers> updateSuppliers(@PathVariable String id, @RequestBody Suppliers suppliers) {
        suppliersService.updateSuppliers(id, suppliers);
        return new ResponseEntity<Suppliers>(suppliers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSuppliers(@PathVariable String id) {
        boolean deleted = suppliersService.deleteSuppliers(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Retorno 200 OK se o usuário foi excluido com sucesso
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorno 400 Bad Request se ocorrer algum erro na exclusão do usuário
        }
    }
    
}
