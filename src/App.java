
import domain.PersonLom;

public class App {
    public static void main(String[] args) {
        // 1. 기본 생성자 + setter 사용
        PersonLom lombok1 = new PersonLom();
        lombok1.setUserId("user123");
        lombok1.setUserName("상우");
        lombok1.setAge(30);
        System.out.println("lombok1 = " + lombok1);

        // 2. @AllArgsConstructor 사용 (전체 필드 생성자)
        // 모든 필드를 넣어야 함 (실무에서는 거의 안 씀)
        PersonLom lombok2 = new PersonLom(
                1,
                "user456",
                "pass123",
                "김상우",
                "ksw@example.com",
                "010",
                "1234-5678",
                30,
                "서울시 강남구",
                "테헤란로 123",
                null, // regDate
                null // modifyDate
        );
        System.out.println("lombok2 = " + lombok2);

        // 3. @Builder 사용 (가장 추천하는 방식)
        PersonLom lombok3 = PersonLom.builder()
                .userId("superwoong")
                .userName("상우")
                .age(99)
                .address1("서울 어딘가")
                .address2("강남역 근처")
                .build();

        System.out.println("lombok3 = " + lombok3);

        // 4. 빌더에 일부만 설정 (null 허용)
        PersonLom lombok4 = PersonLom.builder()
                .id(100)
                .userId("test")
                .userName("테스트유저")
                .build();

        System.out.println("lombok4 = " + lombok4);
    }
}