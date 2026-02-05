package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) {
        // 1. DB 연결 Connection 객체 선언
        Connection conn = null;
        final String USER = "jdbcuser";
        final String PASSWORD = "jdbcuser";
        final String Url = "jdbc:mysql://localhost:3306/jdbc";
        // 2. DB 접속
        try {
            // driver load
            var driverload = Class.forName("com.mysql.cj.jdbc.Driver");
            if (driverload != null) {
                System.out.println("드라이버 로드 성공");
                // jdbc:mysql:// -> jdbc로 db(mysql)에 접속을 하겠따
                // jdbc:mysql://
                // jdbc:mysql://localhost:3306/jdbc
                // localhost server address
                // 3306 port number
                // jdbc -> DB 이름
                conn = DriverManager.getConnection(Url, USER, PASSWORD);
                System.out.println("DB 연결 성공");
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
