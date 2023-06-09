package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Balance;
import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceRepositoryImpl implements BalanceRepository
{
    @Override
    public Balance findBalanceById(Long balanceId)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Balance balance = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "SELECT * FROM balance WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, balanceId);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                balance = new Balance();
                balance.setId(rs.getLong("id"));
                balance.setAmount(rs.getInt("amount"));
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

        return balance;
    }

    @Override
    public Balance findFirst()
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Balance balance = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "SELECT * FROM balance ORDER BY id LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                // Sale 객체 생성 및 값 설정
                balance = new Balance();
                balance.setId(rs.getLong("id"));
                balance.setAmount(rs.getInt("amount"));
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

        return balance;
    }

    public void update(Balance balance)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "UPDATE balance SET amount = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, balance.getAmount());
            pstmt.setLong(2, balance.getId());
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
