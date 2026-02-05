package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest2 {

    public static void main(String[] args) {
        Connection conn = null;
        final String USER = "jdbcuser";
        final String PASSWORD = "jdbcuser";
        final String url = "jdbc:mysql://localhost:3306/jdbc";
        try {
            var driverload = Class.forName("com.mysql.cj.jdbc.Driver");
            if (driverload != null) {
                conn = DriverManager.getConnection(url, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패\n" + e.getMessage());
        } catch (SQLException sqle) {
            System.out.println("Sql Error 발생\n" + sqle.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}