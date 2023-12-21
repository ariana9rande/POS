package hjh.spring.POS.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig
{
    private static final String URL = "jdbc:mysql://localhost:3306/hjh_POS";
    private static final String USERNAME = "hjh";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close(AutoCloseable resource)
    {
        if (resource != null)
        {
            try
            {
                resource.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
