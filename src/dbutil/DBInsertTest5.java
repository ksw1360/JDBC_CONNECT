package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonRe;
import domain.PersonVO;
import repository.UsersDAOImpl;

public class DBInsertTest5 {
    public static void main(String[] args) {
        // DB 연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        // Record 객체의 역활은 값을 변경없이 전달, 받는 역활.
        PersonRe vo1 = new PersonRe(1, "testuser10", "testuserPw", "testuser10", "testuser10@navercom",
                "010", "234-4678", 20,
                "서울시", "강동구 어딘가", null, null);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "insert into Persons(userId, userPw, userName,  userEmail, phone1, phone2, age, address1, address2)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            // 3. PreparedStatement 객체 생성
            // 변경불가
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo1.userId());
            pstmt.setString(2, vo1.userPw());
            pstmt.setString(3, vo1.userName());
            pstmt.setString(4, vo1.userEmail());
            pstmt.setString(5, vo1.phone1());
            pstmt.setString(6, vo1.phone2());
            pstmt.setInt(7, vo1.age());
            pstmt.setString(8, vo1.address1());
            pstmt.setString(9, vo1.address2());
            // PreparedStatement 1. 편의성 2. 보안성(값 겂증)

            System.out.println(pstmt);

            // sql 실행 - 메서드에 매개변수 없슈
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println(result + " Rows 입력 완료");
            } else {
                System.out.println("입력 실패");
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패\n" + e.getMessage());
        }
    }
}
