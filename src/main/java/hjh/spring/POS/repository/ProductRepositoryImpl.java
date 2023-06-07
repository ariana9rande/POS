package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Product;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository
{

    @Override
    public void saveProduct(Product product)
    {

    }

    @Override
    public Product findProductByCode(String code)
    {
        return null;
    }

    @Override
    public List<Product> getAllProducts()
    {
        return null;
    }

    // 기타 필요한 메서드 작성
}
