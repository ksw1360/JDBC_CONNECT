package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "Select * from Persons where id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);
            List<PersonVO> list = new ArrayList<>();

            ResultSet rs = pstmt.executeQuery();

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
                        rs.getString("address2"));
                // rs.getTimestamp("regDate"),
                // rs.getTimestamp("modifydate"));

                list.add(vo); // 리스트에 추가!
                System.out.println(vo); // 한 행씩 출력
                System.out.println("-----------------------------");
            }

            if (list.isEmpty()) {
                System.out.println("데이터가 없습니다.");
            } else {
                System.out.println("총 " + list.size() + "행 조회됨");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
