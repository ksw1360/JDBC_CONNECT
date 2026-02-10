package dbutil.test;

import java.util.Scanner;
import repository.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {

    private static final Users repository = new UsersDAOImpl();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in, "cp949")) { // try-with-resources 추천

            System.out.print("메뉴(i:추가, s:조회, u:수정, d:삭제, q:종료) → ");
            String menu = scanner.next().trim().toLowerCase();

            if (menu.isEmpty()) {
                System.out.println("메뉴를 입력해주세요.");
                return;
            }

            char command = menu.charAt(0);

            switch (command) {
                case 'q' -> {
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                }

                case 'i' -> handleInsert();
                case 's' -> handleSearch();
                case 'u' -> handleUpdate();
                case 'd' -> handleDelete();

                default -> System.out.println("잘못된 메뉴입니다. (i/s/u/d/q 만 가능)");
            }

        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
        }
    }

    private static void handleInsert() {
        UserVO user = UserVO.builder()
                .userId("user202602")
                .userName("user202602")
                .userPw("password")
                .userEmail("user202602@test22.com")
                .build();

        int result = repository.UserAdd(user);

        System.out.println(result > 0 ? "추가 성공" : "추가 실패");
    }

    private static void handleSearch() {
        String email = "user202602@test22.com";
        var list = repository.UserSearch(email);

        if (list == null || list.isEmpty()) {
            System.out.println("조회 결과 없음");
            return;
        }

        list.stream().forEach(s -> System.out.println(s));
    }

    private static void handleUpdate() {
        String email = "user202602@test22.com";
        var list = repository.UserSearch(email);

        if (list == null || list.isEmpty()) {
            System.out.println("수정할 사용자를 찾을 수 없습니다.");
            return;
        }

        UserVO origin = list.get(0);
        origin.setAddress1("수정된 주소2");
        UserVO neworigin = list.get(0);

        System.out.println("neworigin.getId() : " + neworigin.getId());
        // UserVO modified = UserVO.builder()
        // .userId(origin.getUserId())
        // .userName(origin.getUserName() + "_updated")
        // .userPw(origin.getUserPw())
        // .userEmail(origin.getUserEmail())
        // .build();

        int result = repository.UserMod(origin, neworigin);

        System.out.println(result > 0 ? "수정 성공" : "수정 실패");
    }

    private static void handleDelete() {
        String email = "user202602@test22.com";
        var list = repository.UserSearch(email);

        if (list == null || list.isEmpty()) {
            System.out.println("삭제할 사용자를 찾을 수 없습니다.");
            return;
        }

        UserVO user = list.get(0);
        int result = repository.UserDel(user);

        System.out.println(result > 0 ? "삭제 성공" : "삭제 실패");
    }
}

/*
 * package dbutil.test;
 * 
 * import java.util.Scanner;
 * import repository.UserVO;
 * import repository.Users;
 * import repository.UsersDAOImpl;
 * 
 * public class RepositoryTest {
 * 
 * private static Users repository = new UsersDAOImpl();
 * 
 * public static void main(String[] args) {
 * // 레코드 추가
 * try {
 * var scanner = new Scanner(System.in, "cp949");
 * var menu = scanner.next();
 * UserVO searchResult = null;
 * int testresult = 0;
 * menu = menu.toLowerCase(); // 영문자... 대문자를 소문자로 변환
 * searchResult = repository.UserSearch("user202602@test22.com").get(0);
 * if (searchResult != null) {
 * switch (menu.charAt(0)) {
 * case 'i':
 * testresult = repository.UserAdd(new UserVO().builder()
 * .userId("user202602")
 * .userName("user202602")
 * .userPw("password")
 * .userEmail("user202602@test22.com")
 * .build());
 * if (testresult > 0) {
 * System.out.println("email 결과 성공");
 * } else {
 * System.out.println("email 결과 실패");
 * }
 * break;
 * case 's':
 * searchResult = repository.UserSearch("user202602@test22.com").get(0);
 * 
 * if (searchResult != null) {
 * System.out.println("email 결과 성공");
 * System.out.println(searchResult.getUserEmail());
 * } else
 * System.out.println("email 결과 실패");
 * break;
 * case 'u':
 * repository.UserMod(searchResult, searchResult);
 * break;
 * case 'd':
 * // 레코드 삭제
 * testresult = repository.UserDel(searchResult);
 * if (testresult > 0) {
 * System.out.println("email 결과 성공");
 * } else {
 * System.out.println("email 결과 실패");
 * }
 * break;
 * case 'q':
 * System.out.println("프로그램을 종료합니다.");
 * scanner.close();
 * // 프로세스(프로그램) 종료
 * System.exit(0);
 * break;
 * default:
 * System.out.println("메뉴를 잘 못 입력했습니다.");
 * break;
 * }
 * } else {
 * System.out.println("searchResult 가 Null입니다.");
 * }
 * } catch (Exception e) {
 * System.out.println(e.getMessage());
 * }
 * 
 * // var list = repository.UserSearch("user202602", "user202602");
 * // list.stream().forEach(s -> System.out.println(s));
 * 
 * // 사용안함
 * // UserVO testData = UserVO.builder()
 * // .userId("test111")
 * // .userName("test111")
 * // .userPw("test11")
 * // .userEmail("test@test22.com")
 * // .build();
 * // UsersDAOImpl dao = new UsersDAOImpl();
 * // if (dao.UserAdd(testData) != 0) {
 * // System.out.println("성공");
 * // } else {
 * // System.out.println("실패");
 * 
 * // var list = dao.UserAll();
 * 
 * // list.stream().forEach(System.out::println);
 * // }
 * }
 * }
 */