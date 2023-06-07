package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SaleRepositoryImpl implements SaleRepository
{
    @Override
    public void saveSale(Sale sale)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "INSERT INTO sale (total_price) VALUES ( ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, sale.getTotalPrice());

            pstmt.executeUpdate();

            // Sale ID 가져오기
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next())
            {
                long saleId = generatedKeys.getLong(1);
                sale.setId(saleId);

                // SaleItem 저장
                for (SaleItem saleItem : sale.getSaleItems())
                {
                    saveSaleItem(saleItem, saleId, conn);
                }
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
    public void saveSaleItem(SaleItem saleItem, long saleId, Connection conn) throws SQLException
    {
        PreparedStatement pstmt = null;

        try
        {
            String sql = "INSERT INTO sale_item (sale_id, product_id, quantity) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleId);
            pstmt.setLong(2, saleItem.getProduct().getId());
            pstmt.setInt(3, saleItem.getQuantity());

            pstmt.executeUpdate();
        }
        finally
        {
            JdbcConfig.close(pstmt);
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


    @Override
    public List<Sale> getSalesByDateRange(Date startDate, Date endDate)
    {
        // 날짜 범위에 해당하는 판매 내역 조회 로직 작성
        return null;
    }

    @Override
    public int calculateTotalPrice(Long saleId)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "SELECT SUM(si.quantity * p.price) AS total_price " +
                    "FROM sale_item si " +
                    "JOIN product p ON si.product_id = p.id " +
                    "WHERE si.sale_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, saleId);

            rs = pstmt.executeQuery();
            if (rs.next())
            {
                totalPrice = rs.getInt("total_price");
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

        return totalPrice;
    }

    @Override
    public void updateSaleTotalPrice(Long saleId, int totalPrice)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "UPDATE sale SET total_price = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, totalPrice);
            pstmt.setLong(2, saleId);

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
