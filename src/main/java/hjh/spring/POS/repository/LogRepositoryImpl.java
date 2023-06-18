package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Log;
import hjh.spring.POS.service.ProductService;

import java.sql.*;
import java.util.*;

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
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE DATE(log_time) = DATE(NOW()) order by log_time asc";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK) order by log_time asc";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 MONTH) order by log_time asc";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log order by log_time asc";
                            default -> sql;
                        };
            }
            else
            {
                sql = switch (range)
                        {
                            case "daily" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND DATE(log_time) = DATE(NOW()) order by log_time asc";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK) order by log_time asc";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 MONTH) order by log_time asc";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' order by log_time asc";
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
                String actionType = rs.getString("action");

                statistics.add(
                        new Log(actionType, productService.findProductById(productId), sumChangeStock, sumChangeBalance,
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

    @Override
    public Map<String, List<Log>> groupLogsByAction(List<Log> logs)
    {

        Map<String, List<Log>> groupedLogs = new LinkedHashMap<>();

        List<Log> registerLogs = new ArrayList<>();
        List<Log> addLogs = new ArrayList<>();
        List<Log> sellLogs = new ArrayList<>();

        List<Log> sortedLogs = new ArrayList<>(logs);
        sortedLogs.sort(Comparator.comparing(log -> log.getProduct().getId()));

        for (Log log : sortedLogs)
        {
            String action = log.getAction();
            if ("register".equals(action))
            {
                registerLogs.add(log);
            }
            else if ("add".equals(action))
            {
                addLogs.add(log);
            }
            else if ("sell".equals(action))
            {
                sellLogs.add(log);
            }
        }

        groupedLogs.put("register", registerLogs);
        groupedLogs.put("add", addLogs);
        groupedLogs.put("sell", sellLogs);

        return groupedLogs;
    }

    @Override
    public Map<String, Map<String, Integer>> calculateLogs(Map<String, List<Log>> groupedLogs)
    {
        Map<String, Map<String, Integer>> statistics = new HashMap<>();

        for (Map.Entry<String, List<Log>> entry : groupedLogs.entrySet())
        {
            String action = entry.getKey();
            List<Log> logs = entry.getValue();

            Map<String, Integer> actionStatistics = new LinkedHashMap<>();

            for (Log log : logs)
            {
                String productName = log.getProduct().getName();
                int changeAmount = log.getChangeStock();
                actionStatistics.put(productName, actionStatistics.getOrDefault(productName, 0) + changeAmount);
            }

            statistics.put(action, actionStatistics);
        }

        return statistics;
    }
}
