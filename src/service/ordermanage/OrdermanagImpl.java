package service.ordermanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dto.OrderDto;
import dto.UserDto;
import repository.OdersDAOimpl;
import repository.Orders;
import repository.OrdersVO;
import repository.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class OrdermanagImpl implements Ordermanage {
    Users userRepository = new UsersDAOImpl();
    Orders orderRepository = new OdersDAOimpl();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    OrderDto userDto = null;

    @Override
    public boolean createOrder(OrderDto order, UserDto userDto) {
        OrdersVO newOrder = OrdersVO.builder()
                .orderList(order.getOrderList())
                .orderNUm(order.getOrderNUm())
                .price(order.getPrice())
                .userId(userDto.getUserId())
                .build();

        return orderRepository.insertOrder(newOrder);
    }

    @Override
    public boolean deleteOrder(OrderDto order, UserDto userDto) {
        // 삭제 작업은 Orders 테이블의 id로 삭제를 진행
        // 사용자 확인 작업
        List<UserVO> userinfo = userRepository.UserSearch(userDto.getUserEmail());
         //UserVO userinfo = userRepository.UserSearch(userDto.getUserEmail()).get(0);
        if (!userinfo.isEmpty())
        {
            // UserDTo.getUserPW()는 삭제를 위해 입력한 패스워드를 저장.
            //userinfo.get().getUserPw()는 DB에 있는 사용자의 패스워드
            if (userDto.getUserPw().equals(userinfo.get(0).getUserPw()))
                //.get().getUserPw()))
        }else return false;
        orderRepository.deleteOrder(order.getId());
        return false;
    }

    @Override
    public boolean modifyOrder(OrderDto order) {
        Optional<UserVO> user = userRepository.UserSearch2(userDto.getOrderEmail);
        // userRepository.UserSearch(userDto.getId());
        if (user.isPresent()) {
            OrdersVO ordervo = OrdersVO.builder()
                    .id(order.getId())
                    .userId(order.getOrderList())
                    .orderNUm(order.getOrderNUm())
                    .price(order.getPrice())
                    .build();

            return orderRepository.modifyOrder(ordervo);

        }
        return false;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrdersVO> ordersVOList = orderRepository.OdersAll();
        List<OrderDto> ordersList = new ArrayList<>();
        for (OrdersVO vo : ordersVOList) {
            ordersList(OrderDto.builder()
                    .id(vo.getId())
                    .orderList(vo.getOrderList())
                    .orderNUm(vo.getOrderNUm())
                    .price(vo.price)
                    .userId(vo.getUserId())
                    .orderDate(sf.format(vo.getOrderDate()))
                    .build());

        }
        return ordersList;
    }

    private void ordersList(OrderDto orderDto) {

    }

    @Override
    public List<OrderDto> findList(UserDto dto) {
        // 사용자가 주문한 주문 리스트 출력
        // 1. 사용자 정보 : userId를 불러서
        // 2. orderRepository
        List<OrdersVO> ordersVOList = orderRepository.OderSearch(dto.getUserId());
        List<OrderDto> ordersList = new ArrayList<>();
        for (OrdersVO vo : ordersVOList) {
            ordersList(OrderDto.builder()
                    .id(vo.getId())
                    .orderList(vo.getOrderList())
                    .orderNUm(vo.getOrderNUm())
                    .price(vo.price)
                    .userId(vo.getUserId())
                    .orderDate(sf.format(vo.getOrderDate()))
                    .build());

        }
        return ordersList;
    }

    @Override
    public List<OrderDto> findid(UserDto dto) {
        // 사용자가 주문한 주문 리스트 출력
        // 1. 사용자 정보 : userId를 불러서
        // 2. orderRepository
        List<OrdersVO> ordersVOList = orderRepository.OderSearch(dto.getUserId());
        List<OrderDto> ordersList = new ArrayList<>();
        for (OrdersVO vo : ordersVOList) {
            ordersList(OrderDto.builder()
                    .id(vo.getId())
                    .orderList(vo.getOrderList())
                    .orderNUm(vo.getOrderNUm())
                    .price(vo.price)
                    .userId(vo.getUserId())
                    .orderDate(sf.format(vo.getOrderDate()))
                    .build());

        }
        return ordersList;
    }
}
