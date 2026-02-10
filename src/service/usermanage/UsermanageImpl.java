package service.usermanage;

import dto.UserDto;
import repository.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UsermanageImpl implements Usermanage {

    private final Users userRepository = new UsersDAOImpl();

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // UserVO → UserDto 변환 (재사용)
    private UserDto toDto(UserVO vo) {
        if (vo == null)
            return null;

        return UserDto.builder()
                .id(vo.getId())
                .userId(vo.getUserId())
                .userPw(vo.getUserPw())
                .userName(vo.getUserName())
                .userEmail(vo.getUserEmail())
                .phone1(vo.getPhone1())
                .phone2(vo.getPhone2())
                .age(vo.getAge())
                .address1(vo.getAddress1())
                .address2(vo.getAddress2())
                .regDate(formatDate(vo.getRegDate()))
                .modifydate(formatDate(vo.getModifydate()))
                .build();
    }

    // Date → String 안전 변환
    private String formatDate(java.util.Date date) {
        return (date != null) ? DATE_FORMATTER.format(date) : null;
    }

    // UserDto → UserVO 변환 (필요한 필드만)
    private UserVO toVo(UserDto dto) {
        if (dto == null)
            return null;

        return UserVO.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .userPw(dto.getUserPw())
                .userName(dto.getUserName())
                .userEmail(dto.getUserEmail())
                .phone1(dto.getPhone1())
                .phone2(dto.getPhone2())
                .age(dto.getAge())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .build();
    }

    @Override
    public boolean userRegister(UserDto userDto) {
        UserVO vo = toVo(userDto);
        return userRepository.UserAdd(vo) > 0;
    }

    @Override
    public boolean userModify(UserDto userDto) {
        if (userDto == null || userDto.getUserId() == null) {
            return false;
        }

        UserVO vo = toVo(userDto);
        // 기존 레코드와 비교할 원본이 필요하면 여기서 조회 후 비교 가능
        // 현재는 단순 업데이트로 가정
        return userRepository.UserMod(vo, vo) > 0; // ← 실제로는 origin과 modified 구분 필요
    }

    @Override
    public UserDto searchByUserId(String userId) {
        // 현재 DAO가 email로만 검색 가능 → 필요하면 DAO 메서드 추가 권장
        // 임시로 email이 userId라고 가정하거나, DAO 개선 필요
        return null; // ← TODO: userId로 검색하는 로직 구현 필요
    }

    @Override
    public UserDto searchByEmail(String email) {
        List<UserVO> list = userRepository.UserSearch(email);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return toDto(list.get(0));
    }

    @Override
    public List<UserDto> searchAll() {
        List<UserVO> voList = userRepository.UserAll();
        if (voList == null)
            return List.of();

        List<UserDto> dtoList = new ArrayList<>();
        for (UserVO vo : voList) {
            dtoList.add(toDto(vo));
        }
        return dtoList;
    }

    @Override
    public boolean userDelete(UserDto userDto) {
        if (userDto == null || userDto.getId() == 0) {
            return false;
        }
        UserVO vo = toVo(userDto);
        return userRepository.UserDel(vo) > 0;
    }
}