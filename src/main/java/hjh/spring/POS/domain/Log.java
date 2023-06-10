package hjh.spring.POS.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Log
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private Product product;
    private int changeStock;
    private int changeBalance;
    private Timestamp timestamp;

    public Log()
    {

    }

    public Log(Product product, int changeStock, int changeBalance, Timestamp timestamp)
    {
        this.product = product;
        this.changeStock = changeStock;
        this.changeBalance = changeBalance;
        this.timestamp = timestamp;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getChangeStock()
    {
        return changeStock;
    }

    public void setChangeStock(int changeStock)
    {
        this.changeStock = changeStock;
    }

    public int getChangeBalance()
    {
        return changeBalance;
    }

    public void setChangeBalance(int changeBalance)
    {
        this.changeBalance = changeBalance;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }
}
