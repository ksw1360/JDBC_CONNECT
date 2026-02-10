package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 도구 : Connection 객체를 반환하는 도구 생성
    static String url = "jdbc:mysql://localhost:3306/jdbc";
    static String user = "jdbcuser";
    static String password = "jdbcuser";
    static Connection result = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Load 실패\n" + e.getMessage());
        }
    }

    public static Connection GetConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        if (conn != null)
            result = conn;
        else
            result = null;

        return result;
    }
}
