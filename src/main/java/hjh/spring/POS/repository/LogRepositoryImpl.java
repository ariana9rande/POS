package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Log;
import hjh.spring.POS.service.ProductService;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogRepositoryImpl implements LogRepository
{
    private final ProductService productService;

    public LogRepositoryImpl(ProductService productService)
    {
        this.productService = productService;
    }

    public void saveLog(Log log)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "INSERT INTO log (action, product_id, change_stock, change_balance) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, log.getAction());
            pstmt.setLong(2, log.getProduct().getId());
            pstmt.setInt(3, log.getChangeStock());
            pstmt.setInt(4, log.getChangeBalance());
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
    public List<Log> getLogs(String action, String range)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Log> statistics = new ArrayList<>();

        try
        {
            conn = JdbcConfig.getConnection();
            String sql = "";


            if (action.equals("all"))
            {
                sql = switch (range)
                        {
                            case "daily" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE DATE(log_time) = DATE(NOW())";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK)";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE YEAR(log_time) = YEAR(CURRENT_DATE()) AND MONTH(log_time) = MONTH(CURRENT_DATE())";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log";
                            default -> sql;
                        };
            }
            else
            {
                sql = switch (range)
                        {
                            case "daily" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE action = '" + action + "' AND DATE(log_time) = DATE(NOW())";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE action = '" + action + "' AND log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK)";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE action = '" + action + "' AND YEAR(log_time) = YEAR(CURRENT_DATE()) AND MONTH(log_time) = MONTH(CURRENT_DATE())";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id FROM log WHERE action = '" + action + "'";
                            default -> sql;
                        };
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                Timestamp logTime = rs.getTimestamp("log_time");
                int sumChangeStock = rs.getInt("change_stock");
                int sumChangeBalance = rs.getInt("change_balance");
                Long productId = rs.getLong("product_id");
                statistics.add(new Log(productService.findProductById(productId), sumChangeStock, sumChangeBalance,
                        logTime));
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
        return statistics;
    }
}
