package hjh.spring.POS.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Sale
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    List<SaleItem> saleItems;
    private int totalPrice;

    public Sale()
    {
        this.saleItems = new ArrayList<>();
        this.totalPrice = 0;
    }

    public void addSaleItem(SaleItem saleItem)
    {
        saleItems.add(saleItem);
    }

    public int calculateTotalPrice()
    {
        int total = 0;
        for (SaleItem saleItem : saleItems)
        {
            total += saleItem.getProduct().getPrice() * saleItem.getQuantity();
        }
        totalPrice = total;
        return totalPrice;
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

    public int getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
