package dbutil.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import repository.OdersDAOimpl;
import repository.Orders;
import repository.OrdersVO;

public class RepositoryOrdersTest {
    private static Orders ordersRepo = new OdersDAOimpl();

    public static void main(String[] args) {

        if (insertTest()) {
            System.out.println("주문 완료");
        } else {
            System.out.println("주문 실패");
        }

        // if (updateTest() > 0) {
        // System.out.println("수정 완료");
        // } else {
        // System.out.println("수정 실패");
        // }

        Calendar cal = Calendar.getInstance();
        cal.set(2026, 1, 9, 0, 0, 0);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(cal.getTime()));
        // selectTest();
        // selectTest2(1111);
        // searchTest3(sf.format(cal.getTime()));

        if (DeleteTest(1) > 0) {
            System.out.println("삭제 완료");
        } else {
            System.out.println("삭제 실패");
        }
    }

    public static boolean insertTest() {
        LocalDateTime now = LocalDateTime.now();
        // pstmt.setTimestamp(1, Timestamp.valueOf(now));
        OrdersVO order = OrdersVO.builder()
                .orderList("노트북")
                .orderNUm(1111)
                .price(1500000)
                .orderDate(Timestamp.valueOf(now))
                .userId("111")
                .build();
        int result = ordersRepo.OdersAdd(order);
        System.out.println(result > 0 ? "추가 성공" : "추가 실패");

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void selectTest() {
        var result = ordersRepo.OdersAll();
        result.stream().forEach(s -> System.out.println(s));
    }

    public static int updateTest() {
        var result = ordersRepo.OrdersMod(new OrdersVO().builder()
                .orderList("청바지")
                .orderNUm(1113)
                .price(130000)
                .orderDate(new Timestamp(System.currentTimeMillis()))
                .userId("testuser1")
                .id(1)
                .build());

        System.out.println(result > 0 ? "추가 성공" : "추가 실패");

        return result;
    }

    public static void selectTest2(int orderNum) {
        var result = ordersRepo.OderSearch(orderNum);
        result.stream().forEach(s -> System.out.println(s));
    }

    public static void searchTest3(String date) {
        var result = ordersRepo.OderSearch(date);
        result.stream().forEach(s -> System.out.println(s));
    }

    public static int DeleteTest(int id) {
        var result = ordersRepo.OdersDel(new OrdersVO().builder().id(id).build());
        System.out.println(result > 0 ? "추가 성공" : "추가 실패");

        return result;
    }
}
