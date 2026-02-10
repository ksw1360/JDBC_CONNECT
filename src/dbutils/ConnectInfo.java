package dbutils;

import java.util.ArrayList;
import java.util.List;
import domain.PersonsVO2;

public class ConnectInfo {
    // 연결을 위한 정보 생성
    public static void Connection() {
        public static String url = "jdbc:mysql://localhost:3306/jdbc";
        public static String user = "jdbcuser";
        public static String password = "jdbcuser";
        public static List<PersonsVO2> list = new ArrayList<>();
    }

}
