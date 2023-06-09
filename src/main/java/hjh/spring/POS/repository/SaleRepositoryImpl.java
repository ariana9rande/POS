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
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, saleId);
            pstmt.setLong(2, saleItem.getProduct().getId());
            pstmt.setInt(3, saleItem.getQuantity());
            pstmt.executeUpdate();

            // 생성된 SaleItem의 ID 가져오기
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next())
            {
                long saleItemId = generatedKeys.getLong(1);
                saleItem.setId(saleItemId);
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

    @Override
    public Sale findFirst()
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Sale sale = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "SELECT * FROM sale ORDER BY id LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                // Sale 객체 생성 및 값 설정
                sale = new Sale();
                sale.setId(rs.getLong("id"));
                // Sale 객체의 다른 속성들도 필요에 따라 설정
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

        return sale;
    }

    public void deleteSaleItem(SaleItem saleItem)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "DELETE FROM sale_item WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleItem.getProduct().getId());
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

    public void deleteSaleById(Long saleId)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "DELETE FROM sale WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleId);
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

}
