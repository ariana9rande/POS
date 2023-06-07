package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface SaleRepository
{
    void saveSale(Sale sale);

    void updateSale(Sale sale);

    void saveSaleItem(SaleItem saleItem, long saleId, Connection conn) throws SQLException;

    List<Sale> getSalesByDateRange(Date startDate, Date endDate);

    int calculateTotalPrice(Long saleId);

    void updateSaleTotalPrice(Long saleId, int totalPrice);
}