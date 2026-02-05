package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBInsertTest {
    public static void main(String[] args) {
        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        // 2. sql 작성
        // 3. Connection 객체에서 statement 객체를 생성.
        // 4. 생성된 Statement 객체를 이용해서 SQL 실행(Insert)
        // 5. 이후 작업이 없으면 종료
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Persons (userId, userPw, userName, userEmail) " +
                    "values ('testuser1','testuser1','testuser1','testuser1@test.com')";
            // Statement() 객체 생성
            var stmt = conn.prepareStatement(sql);
            var result = stmt.executeUpdate();

            if (result == 0) {
                System.out.println("입력 실패");
            } else {
                System.out.println(result + "Rows 입력 성공");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
