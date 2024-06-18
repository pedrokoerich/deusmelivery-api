package br.deusmelivery.deusmelivery.suppliers.service.Impl;
import br.deusmelivery.deusmelivery.suppliers.entity.Suppliers;
import br.deusmelivery.deusmelivery.suppliers.repository.SuppliersRepository;
import br.deusmelivery.deusmelivery.suppliers.service.SuppliersService;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SuppliersServiceImpl implements SuppliersService {
    private final SuppliersRepository suppliersRepository;

    public SuppliersServiceImpl(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }
    
    @Override
    public List<Suppliers> getAllSuppliers() {
        return suppliersRepository.findAll();
    }

    @Override
    public List<Suppliers> getComboSuppliers() {
        return suppliersRepository.findAll();
    }

    @Override
    public Suppliers getSuppliersById(String id) {
        Optional<Suppliers> suppliersOptional = suppliersRepository.findById(id);
        return suppliersOptional.orElse(null);
    }

    @Override
    public boolean postSuppliers(Suppliers suppliers) {
        try {
            suppliersRepository.save(suppliers);
            return true; // Retorna true se a criação for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na criação
        }
    }

    @Override
    public Suppliers updateSuppliers(String id, Suppliers suppliers) {
        if (suppliersRepository.existsById(id)) {
            suppliers.setId(id); // Garante que o ID passado é o mesmo que o ID do pedido
            return suppliersRepository.save(suppliers);
        } else {
            return null; // ou lançar uma exceção indicando que o pedido não foi encontrado
        }
    }

    @Override
    public boolean deleteSuppliers(String id) {
        try {
            suppliersRepository.deleteById(id);
            return true; // Retorna true se a exclusão for bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro na exclusão
        }
    }
    
}