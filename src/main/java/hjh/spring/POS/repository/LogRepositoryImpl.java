package hjh.spring.POS.repository;

import hjh.spring.POS.configuration.JdbcConfig;
import hjh.spring.POS.domain.Log;
import hjh.spring.POS.service.ProductService;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.sql.*;
import java.time.LocalDate;
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
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE DATE(log_time) = DATE(NOW())";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK)";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE YEAR(log_time) = YEAR(CURRENT_DATE()) AND MONTH(log_time) = MONTH(CURRENT_DATE())";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log";
                            default -> sql;
                        };
            }
            else
            {
                sql = switch (range)
                        {
                            case "daily" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND DATE(log_time) = DATE(NOW())";
                            case "weekly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND log_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK)";
                            case "monthly" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "' AND YEAR(log_time) = YEAR(CURRENT_DATE()) AND MONTH(log_time) = MONTH(CURRENT_DATE())";
                            case "all" ->
                                    "SELECT log_time, change_stock, change_balance, product_id, action FROM log WHERE action = '" + action + "'";
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

                statistics.add(new Log(actionType, productService.findProductById(productId), sumChangeStock, sumChangeBalance, logTime));
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

    /**
     * 로그를 액션별로 그룹화하는 메서드
     * @param logs 로그 리스트
     * @return 액션별로 그룹화된 로그 맵
     */
    @Override
    public Map<String, List<Log>> groupLogsByAction(List<Log> logs) {
        // 순서를 유지하기 위해 LinkedHashMap 사용
        Map<String, List<Log>> groupedLogs = new LinkedHashMap<>();

        // 각각의 액션에 대한 리스트 생성
        List<Log> registerLogs = new ArrayList<>();
        List<Log> addLogs = new ArrayList<>();
        List<Log> sellLogs = new ArrayList<>();

        // 로그를 순회하며 해당 액션의 리스트에 추가
        for (Log log : logs) {
            String action = log.getAction();
            if ("register".equals(action)) {
                registerLogs.add(log);
            } else if ("add".equals(action)) {
                addLogs.add(log);
            } else if ("sell".equals(action)) {
                sellLogs.add(log);
            }
        }

        // 원하는 순서대로 리스트를 groupedLogs 맵에 추가
        groupedLogs.put("register", registerLogs);
        groupedLogs.put("add", addLogs);
        groupedLogs.put("sell", sellLogs);

        return groupedLogs;
    }

    /**
     * 액션별로 통계를 계산하는 메서드
     * @param groupedLogs 액션별로 그룹화된 로그 맵
     * @return 액션별 통계 맵
     */
    @Override
    public Map<String, Map<String, Integer>> calculateStatistics(Map<String, List<Log>> groupedLogs)
    {
        // 액션별 통계 맵
        Map<String, Map<String, Integer>> statistics = new HashMap<>();

        // 액션별로 순회하며 통계 계산
        for (Map.Entry<String, List<Log>> entry : groupedLogs.entrySet())
        {
            String action = entry.getKey();
            List<Log> logs = entry.getValue();

            // 특정 액션의 통계 맵
            Map<String, Integer> actionStatistics = new LinkedHashMap<>();

            // 로그를 순회하며 통계 계산
            for (Log log : logs)
            {
                String productName = log.getProduct().getName();
                int changeAmount = log.getChangeStock();
                actionStatistics.put(productName, actionStatistics.getOrDefault(productName, 0) + changeAmount);
            }

            // 액션별 통계 맵에 추가
            statistics.put(action, actionStatistics);
        }

        return statistics;
    }
}
