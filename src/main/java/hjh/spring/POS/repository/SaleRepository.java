package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Sale;

import java.util.Date;
import java.util.List;

public interface SaleRepository
{
    void saveSale(Sale sale);

    List<Sale> getSalesByDateRange(Date startDate, Date endDate);
}