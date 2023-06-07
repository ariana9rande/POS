package hjh.spring.POS.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    List<SaleItem> saleItems = new ArrayList<>();
    private int totalPrice;

    public Sale()
    {

    }

    public Sale(List<SaleItem> saleItems, int totalPrice)
    {
        this.saleItems = saleItems;
        this.totalPrice = totalPrice;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public List<SaleItem> getSaleItems()
    {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems)
    {
        this.saleItems = saleItems;
    }

    public void addSaleItem(SaleItem saleItem)
    {
        saleItems.add(saleItem);
    }

    public int getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
