package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelectTest {
    public static void main(String[] args) {
        // 이전에 추천했던 옵션 추가 (에러 방지용!)
        String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul";
        String user = "jdbcuser";
        String password = "jdbcuser";

        String sql = "SELECT * FROM Persons"; // 표준 대문자

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) { // 중첩 try-with-resources로 자동 close

            // 헤더 출력 (선택사항)
            System.out.println("=== Persons 테이블 데이터 ===");

            while (rs.next()) { // 모든 행 반복!
                // 행 번호 (1부터 시작하게)
                System.out.println("행 번호: " + rs.getRow());

                // 각 컬럼 출력 (라벨 + 값 별도)
                System.out.println("id : " + rs.getLong("id"));
                System.out.println("userId : " + rs.getString("userId"));
                System.out.println("userPw : " + rs.getString("userPw"));
                System.out.println("userName : " + rs.getString("userName"));
                System.out.println("userEmail : " + rs.getString("userEmail"));

                // phone은 phone1, phone2 컬럼 가정 (필요시 phone3 추가)
                String phone = rs.getString("phone1") + "-" + rs.getString("phone2");
                System.out.println("phone : " + phone);

                System.out.println("age : " + rs.getInt("age"));
                System.out.println("address : " + rs.getString("address1"));
                System.out.println("regDate : " + rs.getTimestamp("regDate"));
                System.out.println("modifydate : " + rs.getTimestamp("modifydate"));

                System.out.println("-----------------------------"); // 행 구분선
            }

            // 데이터 없으면 알려주기
            if (rs.getRow() == 0) {
                System.out.println("데이터가 없습니다.");
            }

        } catch (SQLException e) {
            System.out.println("DB 오류 발생 : " + e.getMessage());
            e.printStackTrace(); // 디버깅용 스택 트레이스
        }
    }
}