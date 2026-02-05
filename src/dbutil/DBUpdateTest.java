package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUpdateTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        Date today = new Date();
        var format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "Update Persons "
                    + " Set"
                    + "  phone1 = '02'"
                    + ", phone2 = '588-9991'"
                    + ", age = '20'"
                    + ", address1 = '서울 어딘가~'"
                    + ", modifydate ='" + format0.format(today)
                    + "'Where id = '1'";
            System.out.println(sql);
            // Statement() 객체 생성
            var stmt = conn.prepareStatement(sql);
            var result = stmt.executeUpdate();

            if (result == 0) {
                System.out.println("수정 실패");
            } else {
                System.out.println(result + "Rows 수정 성공");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
