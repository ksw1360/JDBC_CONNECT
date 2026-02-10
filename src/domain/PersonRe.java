package domain;

import java.sql.Timestamp;

// Record 객체 선언 - 클래스 대신 Record를 사용
// 클래스 선언시에 없던 "()" 사용.
// getter 기본설정. Builder class 사용 가능.
// 1. 간결한 객체 정의.
// 2. 메서드 자동 생성.
// 3. 생성자 자동 생성(Builder 객체 사용)
// 4. 불변성(***) - 객체 안에 있는 맴버 변수들은 final 속성을 가지고 이따
public record PersonRe(
        // 1. 맴버변수 선언 위치
        int id, String userId, String userPw, String userName, String userEmail, String phone1, String phone2, int age,
        String address1, String address2, Timestamp regDate, Timestamp modifyDatate) {

    public Object builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }

}
