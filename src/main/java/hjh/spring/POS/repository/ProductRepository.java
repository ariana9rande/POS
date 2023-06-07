package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Product;

import java.util.List;

public interface ProductRepository
{
    void save(Product product);

    Product findProductById(Long id);

    List<Product> getAllProducts();
}
