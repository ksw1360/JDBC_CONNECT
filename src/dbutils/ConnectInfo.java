package dbutils;

import java.util.ArrayList;
import java.util.List;
import domain.PersonsVO2;

public class ConnectInfo {
    // 연결을 위한 정보 생성
    public static void Connection() {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        List<PersonsVO2> list = new ArrayList<>();
    }

}
