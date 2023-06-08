package hjh.spring.POS.service;

import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;
import hjh.spring.POS.repository.SaleRepository;
import org.springframework.stereotype.Service;

public class SaleService
{
    private final SaleRepository saleRepository;
    private Sale currentSale;

    public SaleService(SaleRepository saleRepository)
    {
        this.saleRepository = saleRepository;
    }

    // 현재 판매 객체 가져오기
    public Sale getCurrentSale()
    {
        if (currentSale == null)
        {
            currentSale = new Sale();
        }
        return currentSale;
    }


    // 판매 객체 생성
    public void createSale(Sale sale)
    {
        currentSale = sale;
    }

    // 판매 아이템을 판매 객체에 추가
    public void addSaleItem(SaleItem saleItem)
    {
        if (currentSale != null)
        {
            currentSale.addSaleItem(saleItem);
        }
    }

    // 판매 객체 업데이트
    public void saveSale()
    {
        if (currentSale != null)
        {
            saleRepository.saveSale(currentSale);
        }
        currentSale = null;
    }

//    public void updateSale()
//    {
//        if (currentSale != null)
//        {
//            saleRepository.updateSale(currentSale);
//        }
//    }

    public void setSaleTotalPrice(Long saleId)
    {
        int totalPrice = saleRepository.calculateTotalPrice(saleId);
//        saleRepository.setSaleTotalPrice(saleId, totalPrice);
        saleRepository.setTotalPrice(currentSale, totalPrice);
    }
}
