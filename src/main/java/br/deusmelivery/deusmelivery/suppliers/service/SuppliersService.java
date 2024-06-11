package br.deusmelivery.deusmelivery.suppliers.service;

import java.util.List;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;

public interface SuppliersService {
    List<Suppliers> getAllSuppliers();
    Suppliers getSuppliersById(String id);
    boolean postSuppliers(Suppliers user);
    Suppliers updateSuppliers(String id, Suppliers supplier);
    boolean deleteSuppliers(String id);
}
