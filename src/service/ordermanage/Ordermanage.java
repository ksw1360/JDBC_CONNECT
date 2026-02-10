package service.ordermanage;

import java.util.List;

import dto.OrderDto;
import dto.UserDto;

public interface Ordermanage {
    // 1. 회원 주문 처리
    boolean createOrder(OrderDto order, UserDto userDto);

    boolean modifyOrder(OrderDto order);

    boolean deleteOrder(OrderDto order, UserDto userDto);

    List<OrderDto> findAll();

    List<OrderDto> findid(UserDto dto);

    // 2. 주문 생성
    // 3. 주문 삭제
    // 4. 주문 정보 확인
    List<OrderDto> findList(UserDto dto);
    // 회원 주문 정보 확인(자신의 userId를 활용해서 주문 확인)
    // 관리 주문 정보 확인

}
