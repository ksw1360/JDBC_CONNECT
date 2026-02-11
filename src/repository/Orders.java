package repository;

import java.util.List;
import java.util.Optional;

import domain.orders.OrdersVO;

public interface Orders {

    List<repository.OrdersVO> orderList(repository.OrdersVO order);

    int OrdersMod(repository.OrdersVO order);

    int OdersDel(repository.OrdersVO order);

    List<repository.OrdersVO> OdersAll();

    List<repository.OrdersVO> OdersSearch(String orderList, int orderNUm);

    int OdersAdd(repository.OrdersVO order);

    List<repository.OrdersVO> OderSearch(int orderNum);

    List<repository.OrdersVO> OderSearch(String date);
}
