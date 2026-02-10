package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonsVO;

public class DBInsertTest3 {
    public static void main(String[] args) {
        // DB 연결을 위한 값을 생성
        PersonsVO vo = new PersonsVO(
                5, "user3", "user3", "user3", "user3@naver.com", "02", "123-4567", 30, "서울 강동구 둔촌동", "양재대로 1300");

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        // DB작업(PreparedStatement)
        // 1. Connection 객체 생성
        // 2. sql 작성 (PreparedStatement 사용할)
        // 0에 sql구문이 있다고 생각하면 됨
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "insert into Personss(userId, userPw, userName,  userEmail, phone1, phone2, age, address1, address2)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            // 3. PreparedStatement 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getUserId());
            pstmt.setString(2, vo.getUserPw());
            pstmt.setString(3, vo.getUserName());
            pstmt.setString(4, vo.getUserEmail());
            pstmt.setString(5, vo.getPhone1());
            pstmt.setString(6, vo.getPhone2());
            pstmt.setInt(7, vo.getAge());
            pstmt.setString(8, vo.getAddress1());
            pstmt.setString(9, vo.getAddress2());
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
