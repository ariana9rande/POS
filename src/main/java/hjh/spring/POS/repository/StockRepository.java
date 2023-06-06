package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Stock;

public interface StockRepository
{
    void saveStock(Stock stock);
}
