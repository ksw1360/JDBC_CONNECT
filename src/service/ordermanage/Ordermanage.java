package service.ordermanage;

import java.util.List;

import dto.UserDto;

public interface Ordermanage {

    // 1. 회원 주문 처리
    // --1) 주문 생성
    boolean createOrder(UserDto order, UserDto dto);

    // --2) 주문 수정
    boolean modifyOrder(UserDto order, UserDto dto);

    // --3) 주문 삭제
    boolean deleteOrder(UserDto order, UserDto dto);

    // --4) 주문 정보 확인
    // 회원 주문 정보 확인(자신의 userId를 활용해서 주문 확인)
    List<UserDto> findList(UserDto dto);

    // 관리자 주문 정보 확인(모든 주문 목록을 확인, 특정 userId, 날짜...)
    List<UserDto> findAll();

    List<UserDto> findUserId(String userId);

    List<UserDto> findDate(String dateString);

    // 2. 비회원 주문 처리 (나중에 여러분이 생각해보세용. )

}
