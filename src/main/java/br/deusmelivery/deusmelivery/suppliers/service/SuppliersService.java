package br.deusmelivery.deusmelivery.suppliers.service;

import java.util.List;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import br.deusmelivery.deusmelivery.suppliers.entity.DTO.SuppliersComboDTO;

public interface SuppliersService {
    List<Suppliers> getAllSuppliers();
    List<SuppliersComboDTO> getComboSuppliers(Long filter);
    Suppliers getSuppliersById(Long id);
    boolean postSuppliers(Suppliers user);
    Suppliers updateSuppliers(Long id, Suppliers supplier);
    boolean deleteSuppliers(Long id);
}
