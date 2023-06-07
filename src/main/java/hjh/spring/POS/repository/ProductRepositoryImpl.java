package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Member;
import hjh.spring.POS.domain.Product;

import java.sql.*;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository
{

    @Override
    public void save(Product product)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "INSERT INTO product (name, price, stock) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getStock());

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcConfig.close(pstmt);
            JdbcConfig.close(conn);
        }
    }


    @Override
    public Product findProductById(Long id)
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
