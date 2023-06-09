package hjh.spring.POS.service;

import hjh.spring.POS.domain.Product;
import hjh.spring.POS.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class ProductService
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product findProductById(Long id)
    {
        return productRepository.findProductById(id);
    }

    public void registerProduct(Product product)
    {
        productRepository.save(product);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAllProducts();
    }

    public void addProductStock(Long id, int quantity)
    {
        productRepository.addStock(id, quantity);
    }

    public void updateProduct(Product product)
    {
        productRepository.update(product);
    }
}
