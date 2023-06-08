package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;
import hjh.spring.POS.service.ProductService;

import java.sql.*;

public class SaleRepositoryImpl implements SaleRepository
{
    private final ProductService productService;

    public SaleRepositoryImpl(ProductService productService)
    {
        this.productService = productService;
    }

    @Override
    public void saveSale(Sale sale)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "INSERT INTO sale (total_price) VALUES (?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, sale.getTotalPrice());
            pstmt.executeUpdate();

            // 생성된 Sale의 ID 가져오기
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next())
            {
                long saleId = generatedKeys.getLong(1);
                sale.setId(saleId);
            }

            // SaleItem을 저장
            for (SaleItem saleItem : sale.getSaleItems())
            {
                saveSaleItem(saleItem, sale.getId());
            }
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
    public void saveSaleItem(SaleItem saleItem, long saleId)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleId);
            pstmt.setLong(2, saleItem.getProduct().getId());
            pstmt.setInt(3, saleItem.getQuantity());
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
    public void updateSale(Sale sale)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "UPDATE sale SET total_price = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sale.getTotalPrice());
            pstmt.setLong(2, sale.getId());
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

    public void updateSaleItem(SaleItem saleItem)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "UPDATE sale_item SET quantity = ? WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, saleItem.getQuantity());
            pstmt.setLong(2, saleItem.getProduct().getId());
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
    public SaleItem findSaleItemById(Long saleItemId)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SaleItem saleItem = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "SELECT * FROM sale_item WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleItemId);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                saleItem = new SaleItem();
                saleItem.setId(rs.getLong("id"));
                saleItem.setProduct(productService.findProductById(rs.getLong("product_id")));
                saleItem.setQuantity(rs.getInt("quantity"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcConfig.close(rs);
            JdbcConfig.close(pstmt);
            JdbcConfig.close(conn);
        }

        return saleItem;
    }
}
