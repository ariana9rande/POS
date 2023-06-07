package hjh.spring.POS.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Stock
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private int quantity;
    private Date date;

    public Stock(Product product, int quantity, Date date)
    {
        this.product = product;
        this.quantity = quantity;
        this.date = date;
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
