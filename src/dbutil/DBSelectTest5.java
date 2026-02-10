package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonsRe;
import domain.PersonsVO2;

public class DBSelectTest5 {
    public static void main(String[] args) {
        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        List<PersonsVO2> list = new ArrayList<>();

        // DB 작업
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "select * from Personss where id <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 10);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();
            // 결과 처리(ResultSet에 들어간 쿼리를 처리)
            while (rs.next()) { // rs.next() 반환값은 boolean
                PersonsRe vo = new PersonsRe(rs.getInt("id"), rs.getString("userId"), rs.getString("userPw"),
                        rs.getString("userName"), rs.getString("userEmail"), rs.getString("phone1"),
                        rs.getString("phone2"), rs.getByte("age"), rs.getString("address1"), rs.getString("address2"),
                        rs.getTimestamp("regDate"), rs.getTimestamp("modifyDate"));

                // System.out.println("Record 객체 " + vo);

                list.add(0, new PersonsVO2().builder()
                        .id(vo.id())
                        .userId(vo.userId())
                        .userPw(vo.userPw())
                        .userName(vo.userName())
                        .userEmail(vo.userEmail())
                        .phone1(vo.phone1())
                        .phone2(vo.phone2())
                        .age(vo.age())
                        .address1(vo.address1())
                        .address2(vo.address2())
                        .regDate(vo.regDate())
                        .modifyDate(vo.modifyDatate())
                        .build());
            }

            list.stream().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
