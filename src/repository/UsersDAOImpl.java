package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;

public class UsersDAOImpl implements Users {

    // 멤버 변수 선언.
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String user = "jdbcuser";
    private String password = "jdbcuser";

    @Override
    public int UserAdd(UserVO user) {
        // INSERT
        int result = 0;
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "insert into Persons(userId, userPw, userName,  userEmail, phone1, phone2, age, address1, address2)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setString(5, user.getPhone1());
            pstmt.setString(6, user.getPhone2());
            pstmt.setInt(7, user.getAge());
            pstmt.setString(8, user.getAddress1());
            pstmt.setString(9, user.getAddress2());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<UserVO> UserAll() {
        // SELECT 전체
        List<UserVO> list = new ArrayList<>();
        // try (Connection conn = DriverManager.getConnection(this.url, this.user,
        // this.password)) {
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Select * From Persons";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO vo = new UserVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserPw(rs.getString("userPw"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserEmail(rs.getString("userEmail"));
                vo.setPhone1(rs.getString("phone1"));
                vo.setPhone2(rs.getString("phone2"));
                vo.setAge(rs.getInt("age"));
                vo.setAddress1(rs.getString("address1"));
                vo.setAddress2(rs.getString("address2"));
                vo.setRegDate(rs.getTimestamp("regDate"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return list;
    }

    @Override
    public int UserDel(UserVO user) {
        // DELETE
        int result = 0;
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Delete From Persons Where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public int UserMod(UserVO before, UserVO after) {
        // UPDATE
        int result = 0;
        LocalDateTime now = LocalDateTime.now();
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Update Persons"
                    + " Set"
                    + " userId = ?"
                    + ", userPw = ?"
                    + ", userName = ?"
                    + ", userEmail = ?"
                    + ", phone1 = ?"
                    + ", phone2 = ?"
                    + ", age = ?"
                    + ", address1 = ?"
                    + ", address2 = ?"
                    + ", modifyDate = ?"
                    + " Where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, after.getUserId());
            pstmt.setString(2, after.getUserPw());
            pstmt.setString(3, after.getUserName());
            pstmt.setString(4, after.getUserEmail());
            pstmt.setString(5, after.getPhone1());
            pstmt.setString(6, after.getPhone2());
            pstmt.setInt(7, after.getAge());
            pstmt.setString(8, after.getAddress1());
            pstmt.setString(9, after.getAddress2());
            pstmt.setTimestamp(10, Timestamp.valueOf(now));
            pstmt.setLong(11, before.getId());

            // System.out.println(pstmt);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<UserVO> UserSearch(String userid, String userName) {
        // SELECT userid, userName
        List<UserVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Select * From Persons Where userId = ? And userName = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, userName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO vo = new UserVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserPw(rs.getString("userPw"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserEmail(rs.getString("userEmail"));
                vo.setPhone1(rs.getString("phone1"));
                vo.setPhone2(rs.getString("phone2"));
                vo.setAge(rs.getInt("age"));
                vo.setAddress1(rs.getString("address1"));
                vo.setAddress2(rs.getString("address2"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifydate(rs.getTimestamp("modifyDate"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return list;
    }

    @Override
    public List<UserVO> UserSearch(String userEmail) {
        // SELECT userEmail
        List<UserVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Select * From Persons Where userEmail = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO vo = new UserVO(); // UserVO로 통일
                vo.setId(rs.getInt("id"));
                vo.setUserId(rs.getString("userId"));
                vo.setUserPw(rs.getString("userPw"));
                vo.setUserName(rs.getString("userName"));
                vo.setUserEmail(rs.getString("userEmail"));
                vo.setPhone1(rs.getString("phone1"));
                vo.setPhone2(rs.getString("phone2"));
                vo.setAge(rs.getInt("age"));
                vo.setAddress1(rs.getString("address1"));
                vo.setAddress2(rs.getString("address2"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifydate(rs.getTimestamp("modifyDate"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return list;
    }

    public Optional<UserVO> userSearch(String userEmail) {
        // SELECT userEmail
        Optional<UserVO> result = null;
        try (Connection conn = DBUtil.GetConnection()) {
            String sql = "Select * From Persons Where userEmail = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = Optional.of(UserVO.builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEmail(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getByte("age"))
                        .address1(rs.getString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifydate(rs.getTimestamp("modifyDate"))
                        .build());
            }
        } catch (SQLException e) {
            System.err.println("DB작업 실패 : \n" + e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<UserVO> UserSearch2(String userEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UserSearch2'");
    }
}
