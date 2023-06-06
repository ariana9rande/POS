package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Product;

import java.util.List;

public interface ProductRepository
{
    void saveProduct(Product product);

    Product findProductByCode(String code);

    List<Product> getAllProducts();
}
