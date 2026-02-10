package service.usermanage;

import java.util.ArrayList;
import java.util.List;
import domain.users.UserVO;
import dto.UserDto;
import repository.Users;
import repository.UsersDAOImpl;

public class UsermanageImpl implements Usermanage {

    // DB 작업을 할 수 있는 객체를 호출 작업 진행...
    // 인터페이스를 통한 객체 호출...
    Users userRepository = new UsersDAOImpl();

    @Override
    public List<UserDto> searchAll() {
        List<UserDto> UserDtoList = new ArrayList<>();
        List<UserVO> userVOList = userRepository.userAll();
        for (UserVO vo : userVOList) {
            UserDto dto = UserDto.toUserDTO(vo);
            UserDtoList.add(dto);
        }
        return UserDtoList;
    }

    @Override
    public UserDto searchOne(String userEmail) {
        UserVO vo = userRepository.userSearch(userEmail).get();
        UserDto dto = UserDto.toUserDTO(vo);
        return dto;
    }

    @Override
    public boolean userDelete(UserDto UserDto) {
        // UserDto -> UserVO
        UserVO userVO = dto.UserDto.toUserVO(UserDto);

        if (userRepository.userDel(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userModify(UserDto UserDto) {
        UserVO userVO = dto.UserDto.toUserVO(UserDto);
        if (userRepository.userMod(userVO) != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean userRegister(UserDto UserDto) {
        UserVO userVO = dto.UserDto.toUserVO(UserDto);
        if (userRepository.userAdd(userVO) != 0)
            return true;
        else
            return false;

    }

    @Override
    public UserDto login(String userId, String userPw) {
        UserDto userDTO = UserDto.toUserDTO(
                userRepository.login(userId, userPw).get());
        return userDTO;
    }
}
