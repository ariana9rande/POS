package hjh.spring.POS.service;

import hjh.spring.POS.domain.Product;
import hjh.spring.POS.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public void registerProduct(Product product)
    {
        productRepository.save(product);
    }
}
