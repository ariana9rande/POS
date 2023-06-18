package hjh.spring.POS.repository;

import hjh.spring.POS.model.Product;

import java.util.List;

public interface ProductRepository
{
    void save(Product product);

    Product findProductById(Long id);

    List<Product> findAllProducts();

    void addStock(Long id, int quantity);

    void update(Product product);
}
