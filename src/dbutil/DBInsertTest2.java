package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

import domain.PersonVO;

public class DBInsertTest2 {
    public static void main(String[] args) {
        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        // 2. sql 작성
        // 3. Connection 객체에서 statement 객체를 생성.
        // 4. 생성된 Statement 객체를 이용해서 SQL 실행(Insert)
        // 5. 이후 작업이 없으면 종료
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        PersonVO vo = new PersonVO(2, "testuser2", "testuser2pw", "testuser2", "testuser2@test.com", "02", "444-4444",
                24, "서울시 강동구 어딘가", null, null);
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Persons (userId, userPw, userName, userEmail, phone1, phone2, age, address1) " +
                    "values ('" + vo.getUserId()
                    + "','" + vo.getUserName()
                    + "','" + vo.getUserPw()
                    + "','" + vo.getUserEmail()
                    + "','" + vo.getPhone1()
                    + "','" + vo.getPhone2()
                    + "'," + vo.getAge()
                    + ",'" + vo.getAddress1()
                    + "')";
            System.out.println(sql);
            // Statement() 객체 생성
            var stmt = conn.prepareStatement(sql);
            var result = stmt.executeUpdate();

            if (result == 0) {
                System.out.println("입력 실패");
            } else {
                System.out.println(result + " Rows 입력 성공");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}