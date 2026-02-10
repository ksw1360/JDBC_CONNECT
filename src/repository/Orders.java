package repository;

import java.util.List;

public interface Orders {
    List<OrdersVO> orderList(OrdersVO order);

    int OdersAdd(OrdersVO order);

    int OrdersMod(OrdersVO order);

    int OdersDel(OrdersVO order);

    List<OrdersVO> OdersAll();

    List<OrdersVO> OdersSearch(String orderList, int orderNUm);

    List<OrdersVO> OderSearch(int orderNum);

    List<OrdersVO> OderSearch(String date);

    boolean insertOrder(OrdersVO newOrder);

    void deleteOrder(int id);

    boolean modifyOrder(OrdersVO ordervo);
}
