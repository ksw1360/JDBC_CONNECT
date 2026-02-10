package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;

public class OdersDAOimpl implements Orders {

    // 주문 정보출력()
    @Override
    public List<OrdersVO> orderList(OrdersVO order) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "Select * From Orders Where orderList = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.orderList);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO vo = new OrdersVO(); // OrdersVO 통일
                vo.setOrderList(rs.getString("orderList"));
                vo.setOrderNUm(rs.getInt("orderNUm"));
                vo.setPrice(rs.getInt("price"));
                vo.setOrderDate(rs.getTimestamp("orderDate"));
                vo.setUserId(rs.getString("userId"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return list;
    }

    @Override
    public int OrdersMod(OrdersVO order) {
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "Update Orders"
                    + " Set "
                    + "    orderList = ? "
                    + "   ,orderNUm = ? "
                    + "   ,price = ? "
                    + "   ,orderDate = ? "
                    + "   ,userId = ? "
                    + "Where id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNUm());
            pstmt.setInt(3, order.getPrice());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(5, order.getUserId());
            pstmt.setInt(6, order.getId());

            System.out.println(pstmt);

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public int OdersDel(OrdersVO order) {
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "Delete From Orders Where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getId());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<OrdersVO> OdersAll() {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "Select * From Orders";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO vo = new OrdersVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setOrderList(rs.getString("orderList"));
                vo.setOrderNUm(rs.getInt("orderNUm"));
                vo.setPrice(rs.getInt("price"));
                vo.setOrderDate(rs.getTimestamp("orderDate"));
                vo.setUserId(rs.getString("userId"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<OrdersVO> OdersSearch(String orderList, int orderNUm) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {

            String sql = "Select * From Orders Where orderList = ? And orderNum = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            var rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO vo = new OrdersVO(); // UserVO로 통일
                vo.setOrderList(rs.getString("orderList"));
                vo.setOrderNUm(rs.getInt("orderNUm"));
                vo.setPrice(rs.getInt("price"));
                vo.setOrderDate(rs.getTimestamp("orderDate"));
                vo.setUserId(rs.getString("userId"));

                list.add(vo);
            }
        } catch (Exception e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }

        return list;
    }

    // 주문 추가
    @Override
    public int OdersAdd(OrdersVO order) {
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "insert into Orders(orderList, orderNUm, price, orderDate, userId)"
                    + "values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNUm());
            pstmt.setInt(3, order.getPrice());
            pstmt.setTimestamp(4, order.getOrderDate());
            pstmt.setString(5, order.getUserId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<OrdersVO> OderSearch(int orderNum) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {

            String sql = "Select * From Orders Where orderNum = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNum);

            var rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO vo = new OrdersVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setOrderList(rs.getString("orderList"));
                vo.setOrderNUm(rs.getInt("orderNUm"));
                vo.setPrice(rs.getInt("price"));
                vo.setOrderDate(rs.getTimestamp("orderDate"));
                vo.setUserId(rs.getString("userId"));

                list.add(vo);
            }
        } catch (Exception e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }

        return list;
    }

    @Override
    public List<OrdersVO> OderSearch(String date) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection()) {

            String sql = "Select * From Orders Where orderDate Like ?";
            String searchvalue = '%' + date + '%';

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, searchvalue);

            var rs = pstmt.executeQuery();
            while (rs.next()) {
                OrdersVO vo = new OrdersVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setOrderList(rs.getString("orderList"));
                vo.setOrderNUm(rs.getInt("orderNUm"));
                vo.setPrice(rs.getInt("price"));
                vo.setOrderDate(rs.getTimestamp("orderDate"));
                vo.setUserId(rs.getString("userId"));

                list.add(vo);
            }
        } catch (Exception e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }

        return list;
    }

    @Override
    public boolean insertOrder(domain.orders.OrdersVO order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrder'");
    }

    @Override
    public boolean deleteOrder(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public boolean modifyOrder(domain.orders.OrdersVO order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyOrder'");
    }

    @Override
    public List<domain.orders.OrdersVO> ordersList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordersList'");
    }

    @Override
    public List<domain.orders.OrdersVO> ordersSearch(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordersSearch'");
    }

    @Override
    public List<domain.orders.OrdersVO> ordersSearchDate(String date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordersSearchDate'");
    }

    @Override
    public Optional<domain.orders.OrdersVO> ordersSearch(int orderNum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordersSearch'");
    }
}
