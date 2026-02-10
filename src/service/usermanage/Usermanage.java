package service.usermanage;

import java.util.List;

import dto.UserDto;

public interface Usermanage {

    // 1. 사용자 등록
    boolean userRegister(UserDto userDTO);

    // 2. 사용자 수정
    boolean userModify(UserDto userDTO);

    // 3. 사용자 검색(특정 사용자 검색, 전체 사용자 검색)
    // 특정 사용자
    UserDto searchOne(String userEmail);

    // 전체 사용자
    List<UserDto> searchAll();

    // 4. 사용자 삭제
    boolean userDelete(UserDto userDTO);

    // boolean login(UserDto userDto);

    UserDto login(String userId, String userPw);

}
