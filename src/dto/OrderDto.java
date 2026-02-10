package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    public int id;
    public String orderList;
    public int orderNUm;
    public int price;
    public String orderDate;
    public String userId;
    public String getOrderEmail;
}
