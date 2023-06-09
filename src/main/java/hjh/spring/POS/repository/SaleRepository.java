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

    void saveSaleItem(SaleItem saleItem, long saleId);

    void updateSale(Sale sale);

    void updateSaleItem(SaleItem saleItem);

    SaleItem findSaleItemById(Long saleItemId);

    Sale findFirst();
}