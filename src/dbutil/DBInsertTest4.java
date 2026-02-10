package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import domain.PersonsVO2;

public class DBInsertTest4 {
    public static void main(String[] args) {
        PersonsVO2 vo = new PersonsVO2().builder()
                .userId("sangwoo")
                .userPw("sangwoo")
                .userName("상우")
                .userEmail("ksw1360@gmail.com")
                .phone1("010")
                .phone2("1234-5678")
                .age(99)
                .address1("서울 어딘가")
                .address2("강남역 근처")
                .build();
        // Stream을 사용하여 10개의 데이터를 추가하는 코드를 작성하라
        // PersonsVO2 객체
        List<PersonsVO2> list = new ArrayList<>();
        // list.stream().forEach(s -> insertDB(vo));
        // list.stream().forEach(DBInsertTest4::insertDB);
        IntStream.range(10, 50).forEach(i -> insertDB(new PersonsVO2().builder()
                .userId("sangwoo" + i) // sangwoo0, sangwoo1, ...
                .userPw("sangwoo" + i)
                .userName("상우" + i)
                .userEmail("ksw1360@gmail.com")
                .phone1("010")
                .phone2("123-567" + i % 10)
                .age(20 + i) // 20~29살
                .address1("서울 어딘가")
                .address2("강남역 근처 " + i)
                .build()));
    }

    // InsertDB() 생성
    public static void insertDB(PersonsVO2 vo) {
        try {
            String url = "jdbc:mysql://localhost:3306/jdbc";
            String user = "jdbcuser";
            String password = "jdbcuser";
            String sql = "insert into Personss(userId, userPw, userName,  userEmail, phone1, phone2, age, address1, address2)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            Connection conn = DriverManager.getConnection(url, user, password);
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

            int result = pstmt.executeUpdate();

            System.out.println(vo.getUserId() + " → " + (result > 0 ? "성공" : "실패"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
