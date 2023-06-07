package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Sale;

import java.util.Date;
import java.util.List;

public class SaleRepositoryImpl implements SaleRepository
{
    @Override
    public void saveSale(Sale sale)
    {

    }

    @Override
    public List<Sale> getSalesByDateRange(Date startDate, Date endDate)
    {
        // 날짜 범위에 해당하는 판매 내역 조회 로직 작성
        return null;
    }
}
