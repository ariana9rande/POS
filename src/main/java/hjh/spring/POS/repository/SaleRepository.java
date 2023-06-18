package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;

public interface SaleRepository
{
    void saveSale(Sale sale);

    void saveSaleItem(SaleItem saleItem, long saleId);

    void updateSale(Sale sale);

    void updateSaleItem(SaleItem saleItem);

    SaleItem findSaleItemById(Long saleItemId);

    void deleteSaleItem(Long saleItemId);

    void deleteAllSaleItems();

    void deleteSaleById(Long saleId);
}