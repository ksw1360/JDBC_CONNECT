package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest2 {
    public static void main(String[] args) {
        // 에러 방지 옵션 추가!
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        String sql = "SELECT * FROM Persons";

        List<PersonVO> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Persons 테이블 데이터 ===");

            while (rs.next()) {
                PersonVO vo = new PersonVO(
                        rs.getInt("id"),
                        rs.getString("userId"),
                        rs.getString("userPw"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getInt("age"),
                        rs.getString("address1"),
                        rs.getTimestamp("regDate"),
                        rs.getTimestamp("modifydate"));

                list.add(vo); // 리스트에 추가!
                System.out.println(vo); // 한 행씩 출력
                System.out.println("-----------------------------");
            }

            if (list.isEmpty()) {
                System.out.println("데이터가 없습니다.");
            } else {
                System.out.println("총 " + list.size() + "행 조회됨");
            }

        } catch (SQLException e) {
            System.out.println("DB 오류 발생 : " + e.getMessage());
            e.printStackTrace();
        }
    }
}