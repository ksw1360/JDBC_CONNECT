package service.usermanage;

import java.util.List;
import dto.UserDto;

public interface Usermanage {

    // 사용자 등록
    boolean userRegister(UserDto userDto);

    // 사용자 정보 수정
    boolean userModify(UserDto userDto);

    // 사용자 단건 조회 (ID 기준)
    UserDto searchByUserId(String userId);

    // 사용자 단건 조회 (Email 기준)
    UserDto searchByEmail(String email);

    // 전체 사용자 조회
    List<UserDto> searchAll();

    // 사용자 삭제
    boolean userDelete(UserDto userDto);
}