package repository;

import java.sql.Timestamp;

import domain.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString // userPw toString 메서드 사용
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 멤버변수 사용 생성자
@lombok.Builder
public class OrdersVO {
    public int id;
    public String orderList;
    public int orderNUm;
    public int price;
    public Timestamp orderDate;
    public String userId;
}
