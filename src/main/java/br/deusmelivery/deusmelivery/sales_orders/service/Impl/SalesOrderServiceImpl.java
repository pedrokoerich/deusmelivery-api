package br.deusmelivery.deusmelivery.sales_orders.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.deusmelivery.deusmelivery.products.repository.ProductsRepository;
import br.deusmelivery.deusmelivery.sales_orders.entity.SalesOrder;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.MonthlySalesDTO;
/* import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.MonthlySalesDTO; */
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.ProductsSalesDTO;
import br.deusmelivery.deusmelivery.sales_orders.entity.DTO.SalesOrderDTO;
import br.deusmelivery.deusmelivery.sales_orders.repository.SalesOrderRepository;
import br.deusmelivery.deusmelivery.sales_orders.service.SalesOrderService;
import br.deusmelivery.deusmelivery.suppliers.repository.SuppliersRepository;
import br.deusmelivery.deusmelivery.users.entity.Users;
import br.deusmelivery.deusmelivery.users.repository.UsersRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            dto.setProductName((productsRepository.findById(salesOrder.getProduto())).get().getName());
            dto.setUserName((usersRepository.findById(salesOrder.getCliente())).get().getName());
            dto.setSupplierName((suppliersRepository.findById(Long.parseLong(salesOrder.getFornecedor())).get().getFantasyName()));
            dto.setQuantity(salesOrder.getQuantidade());
            dto.setPrice(salesOrder.getValorUnitario());
            dto.setTotalPrice(salesOrder.getValorTotal());
            dto.setDataEntrega(salesOrder.getDataEntrega());
            dto.setHoraEntrega(salesOrder.getHoraEntrega());
            salesOrderDTOs.add(dto);
        }
        return salesOrderDTOs;
    }

    @Override
    public List<ProductsSalesDTO> getTop5BebidasMaisConsumidas() {
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        Map<Long, Integer> productSalesMap = new HashMap<>();

        // Agrupa e soma as quantidades vendidas por produto
        for (SalesOrder salesOrder : salesOrders) {
            Long productId = salesOrder.getProduto();
            int quantidadeVendida = salesOrder.getQuantidade();
            productSalesMap.put(productId, productSalesMap.getOrDefault(productId, 0) + quantidadeVendida);
        }

        // Cria a lista de DTOs com as quantidades totais vendidas
        List<ProductsSalesDTO> productsSalesDTOs = productSalesMap.entrySet().stream()
                .map(entry -> {
                    ProductsSalesDTO dto = new ProductsSalesDTO();
                    dto.setProductName(productsRepository.findById(entry.getKey()).get().getName());
                    dto.setQuantitySold(entry.getValue());
                    return dto;
                })
                .sorted((dto1, dto2) -> Integer.compare(dto2.getQuantitySold(), dto1.getQuantitySold())) // Ordena em ordem decrescente
                .limit(5) // Limita aos top 5
                .collect(Collectors.toList());

        return productsSalesDTOs;
    }

    @Override
    public List<ProductsSalesDTO> getTop5EstadosMaisConsumidores() {
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        Map<String, Integer> stateSalesMap = new HashMap<>();

        // Agrupa e soma as quantidades vendidas por estado
        final int[] totalSales = {0}; // Usando um array para permitir mutação
        for (SalesOrder salesOrder : salesOrders) {
            Long clienteId = salesOrder.getCliente();
            Users user = usersRepository.findById(clienteId).orElse(null);

            if (user != null) {
                String state = user.getState();
                int quantidadeVendida = salesOrder.getQuantidade();
                stateSalesMap.put(state, stateSalesMap.getOrDefault(state, 0) + quantidadeVendida);
                totalSales[0] += quantidadeVendida; // Acumula o total de vendas
            }
        }

        // Cria a lista de DTOs com os percentuais de vendas por estado
        List<ProductsSalesDTO> productsSalesDTOs = stateSalesMap.entrySet().stream()
                .map(entry -> {
                    ProductsSalesDTO dto = new ProductsSalesDTO();
                    dto.setStateSale(entry.getKey());
                    float percentage = (entry.getValue() * 100f) / totalSales[0];
                        // Arredonda para duas casas decimais
                        BigDecimal bd = new BigDecimal(Float.toString(percentage));
                        bd = bd.setScale(2, RoundingMode.HALF_UP);
                        dto.setPercentageSold(bd.floatValue()); // Assumindo que o DTO tem um campo percentageSold
                        return dto;
                    })
                    .sorted((dto1, dto2) -> Float.compare(dto2.getPercentageSold(), dto1.getPercentageSold())) // Ordena em ordem decrescente
                    .limit(5) // Limita aos top 5
                    .collect(Collectors.toList());

        return productsSalesDTOs;
    }

    @Override
    public List<MonthlySalesDTO> getVendasNosUltimos12Meses() {
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        Map<String, Double> monthlySalesMap = new HashMap<>();
    
        // Agrupa e soma os valores vendidos por mês e categoria
        for (SalesOrder salesOrder : salesOrders) {
            Date dataEntrega = salesOrder.getDataEntrega();
            LocalDate localDate;
            if (dataEntrega instanceof java.sql.Date) {
                localDate = ((java.sql.Date) dataEntrega).toLocalDate();
            } else {
                localDate = dataEntrega.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
    
            String monthYear = localDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
            String category = (productsRepository.findById(salesOrder.getProduto())).get().getCategory();
            String key = monthYear + " - " + category;
    
            double valorTotal = salesOrder.getValorTotal();
            monthlySalesMap.put(key, monthlySalesMap.getOrDefault(key, 0.0) + valorTotal);
        }
    
        // Cria a lista de DTOs com os valores totais vendidos
        List<MonthlySalesDTO> monthlySalesDTOs = monthlySalesMap.entrySet().stream()
                .map(entry -> {
                    MonthlySalesDTO dto = new MonthlySalesDTO();
                    dto.setMonth(entry.getKey().split(" - ")[0]);
                    dto.setCategory(entry.getKey().split(" - ")[1]);
                    dto.setTotalSales(entry.getValue());
                    return dto;
                })
                .sorted(Comparator.comparing(MonthlySalesDTO::getMonth)
                        .thenComparing(MonthlySalesDTO::getCategory)) // Ordena por mês e categoria
                .collect(Collectors.toList());
    
        return monthlySalesDTOs;
    }
    
    @Override
    public List<Users> getTop10ClientesFieis() {
        List<SalesOrder> salesOrders = salesOrderRepository.findAll();
        Map<Long, Integer> userSalesMap = new HashMap<>();
    
        // Agrupa e soma as quantidades compradas por cliente
        for (SalesOrder salesOrder : salesOrders) {
            Long userId = salesOrder.getCliente();
            int quantidadeComprada = salesOrder.getQuantidade();
            userSalesMap.put(userId, userSalesMap.getOrDefault(userId, 0) + quantidadeComprada);
        }
    
        // Ordena os usuários pelo total comprado em ordem decrescente e pega os top 10
        List<Users> topUsers = userSalesMap.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue())) // Ordena em ordem decrescente
                .limit(10) // Limita aos top 10
                .map(entry -> usersRepository.findById(entry.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    
        return topUsers;
    }

     public int getOrdersCountByYear(int year) {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.DAY_OF_YEAR, 1);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.YEAR, year);
        endCal.set(Calendar.MONTH, Calendar.DECEMBER);
        endCal.set(Calendar.DAY_OF_MONTH, 31);

        Date startDate = startCal.getTime();
        Date endDate = endCal.getTime();

        List<SalesOrder> orders = salesOrderRepository.findAllByDataEntregaBetween(startDate, endDate);
        return orders.size();
    }

    public int getOrdersCountForCurrentYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return getOrdersCountByYear(currentYear);
    }

    public int getOrdersCountForCurrentMonth() {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));

        Date startDate = startCal.getTime();
        Date endDate = endCal.getTime();

        List<SalesOrder> orders = salesOrderRepository.findAllByDataEntregaBetween(startDate, endDate);
        return orders.size();
    }
}
