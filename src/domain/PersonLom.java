package domain;
/*
Lombok에서 주로 사용하는 어노테이션
@Setter/@Getter : getter/setter 설정
@ToString : toString 메서드 생성
@EqualsAndHashCode : Equals() 와 hashCode() 메서드를 자동 구현
@Data : Getter , Setter, toString(), EqualsAndHashCode 어노테이션 기능을 모두 포함
@Builder : 복잡한 객체 생성을 안정화하는 Builder를 자동 생성
@AllArgsConstructor : 맴버 변수 전체를 사용하는 생성자를 만들어 줌
@NoArgsConstructor : 개본 생성자를 만들어줌
주의점
    1. 무분멸한 어노테이션의 사용으로 다른 기능과 연결되어 의도하지 않은 동작을 할수 이따
       (Data 어노테이션은 자중하는 것이 좋다)
    2. @Builder만 사용하면 기본 생성자는 생성되지 않음.
       @AllArgsConstructor는 위에 @NoArgsConstructor를 사용하는 경우에 같이 사용
    3. Lombok에 의존시 대처 하기 힘듬.
       Lombok을 사용하지 못하는 경우에 대해서 대비할 필요가 있음.
 */

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonLom {
   private int id;
   private String userId;
   private String userPw;
   private String userName;
   private String userEmail;
   private String phone1;
   private String phone2;
   private int age;
   private String address1;
   private String address2;
   private Timestamp regDate;
   private Timestamp modifyDate; // modifydate → modifyDate (camelCase 추천)
}
