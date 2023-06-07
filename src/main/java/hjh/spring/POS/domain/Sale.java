package hjh.spring.POS.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Sale
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private int quantity;
    private int totalPrice;

    public Sale(Product product, int quantity, int totalPrice)
    {
        this.product = product;
        this.quantity = quantity;
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

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
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
