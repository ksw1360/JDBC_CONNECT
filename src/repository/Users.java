package repository;

import java.util.List;
import java.util.Optional;

public interface Users {
    // 레코드 추가
    int UserAdd(UserVO user);

    // 레코드 수정
    int UserMod(UserVO before, UserVO after);

    // 레코드 삭제
    int UserDel(UserVO user);

    // 레코드 조회
    List<UserVO> UserAll();

    // 1. 전체 조회
    List<UserVO> UserSearch(String userid, String userName);

    List<UserVO> UserSearch(String userid);

    List<UserVO> UserSearch(int id);
    // 2. 조건 조회 - (userid(unique), name, email(unique 처리 안해도 unique))

    Optional<UserVO> UserSearch2(String userEmail);
}
