package controller;

import java.util.List;

import dto.OrderDTO;
import dto.UserDto;
import service.ordermanage.OrdermanagImpl;
import service.ordermanage.Ordermanage;
import service.usermanage.Usermanage;
import service.usermanage.UsermanageImpl;

public class OrderProgramController {
    private Ordermanage orderService = new OrdermanagImpl();
    private Usermanage userService = new UsermanageImpl();

    // 메서드 구현
    // 1. 회원 가입
    public boolean join(String userId, String userPw, String userName, String userEmail, String userPhone,
            int Age,
            String Address1, String Address2) {
        String digits = userPhone.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            // 에러 처리 (필요하면 throw나 로그)
            throw new IllegalArgumentException("한국 휴대폰 번호는 11자리 숫자여야 합니다: " + userPhone);
        }

        String Phone1 = digits.substring(0, 3); // 010
        String Phone2 = digits.substring(3); // 62592887 (8자리)
        // String Phone1 = "";
        // String Phone2 = "";
        // if (userPhone.contains("-")) {
        // String[] parts = userPhone.split("-", 2);
        // Phone1 = parts[0];
        // Phone2 = parts[1];
        // } else {
        // Phone1 = userPhone.substring(0, 3);
        // Phone2 = userPhone.substring(4, 8);
        // }

        UserDto userDTO = UserDto.builder()
                // .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userEmail(userEmail)
                .phone1(Phone1)
                .phone2(Phone2)
                .age(Age)
                .address1(Address1)
                .address2(Address2)
                .build();

        return userService.userRegister(userDTO);
    }

    // 2. 회원 가입 정보 확인
    public UserDto UserInfo(String Email) {
        return userService.searchOne(Email);
    }

    // 3. 회원 가입 정보 수정
    public boolean userModify(int ld, String userId, String userPw, String userName, String userEmail,
            String userPhone,
            int Age, String Address1, String Address2) {

        String digits = userPhone.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            // 에러 처리 (필요하면 throw나 로그)
            throw new IllegalArgumentException("한국 휴대폰 번호는 11자리 숫자여야 합니다: " + userPhone);
        }

        String Phone1 = digits.substring(0, 3); // 010
        String Phone2 = digits.substring(3); // 62592887 (8자리)
        // String[] parts = userPhone.split("-", 2);
        // String Phone1 = parts[0];
        // String Phone2 = parts[1];

        UserDto userDTO = UserDto.builder()
                .id(Age)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userEmail(userEmail)
                .phone1(Phone1)
                .phone2(Phone2)
                .age(Age)
                .address1(Address1)
                .address2(Address2)
                .build();

        return userService.userModify(userDTO);
    }

    // 4. 회원 탈퇴
    public boolean revokeUser(UserDto userDTO) {
        // 기존 DB에 있는 사용자 정보 : user
        UserDto user = userService.searchOne(userDTO.getUserEmail());
        // DB에 있는 PW와 userDTO에 있는 pw를 비교해서 같으면 삭제
        // 검증처리
        if (user.getUserPw().equals(userDTO.getUserPw())) {
            return userService.userDelete(userDTO);
        } else
            return false;
    }

    // 5. 주문 처리(생성)
    public boolean createOrder(UserDto userDTO, String ordrList, int price) {
        int num = (int) (Math.random() * 100);

        OrderDTO orderDTO = OrderDTO.builder()
                .orderList(ordrList)
                .orderNum(num)
                .price(price)
                // .userId(userDTO.getUserId())
                .build();
        return orderService.createOrder(orderDTO, userDTO);
        // .createOrder(orderDTO, userDTO);
    }

    // 6. 주문 조회
    public List<OrderDTO> getOrders(UserDto userDTO) {
        return orderService.findList(userDTO);
    }

    // 7. 주문 수정
    public boolean modifyOrder(UserDto userDTO, OrderDTO modify) {
        orderService.modifyOrder(modify, userDTO);
        return false;
    }

    // 8. 주문 삭제
    public boolean removeOrder(UserDto userDTO, OrderDTO orderDTO) {
        return orderService.deleteOrder(orderDTO, userDTO);
    }

    // 9. 로그인
    public UserDto login(String userId, String userPw) {
        return userService.login(userId, userPw);
    }

    public boolean userModify(long id, String userId, String userPw, String userName, String userEmail,
            String userPhone, int userAge, String userAddress1, String userAddress2) {
        String digits = userPhone.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            // 에러 처리 (필요하면 throw나 로그)
            throw new IllegalArgumentException("한국 휴대폰 번호는 11자리 숫자여야 합니다: " + userPhone);
        }

        String Phone1 = digits.substring(0, 3); // 010
        String Phone2 = digits.substring(3); // 62592887 (8자리)
        // String[] parts = userPhone.split("-", 2);
        // String Phone1 = parts[0];
        // String Phone2 = parts[1];

        UserDto userDTO = UserDto.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userEmail(userEmail)
                .phone1(Phone1)
                .phone2(Phone2)
                .age(userAge)
                .address1(userAddress1)
                .address2(userAddress2)
                .build();

        return userService.userModify(userDTO);
    }
}
