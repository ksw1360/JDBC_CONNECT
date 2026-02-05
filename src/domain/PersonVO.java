package domain;

import java.sql.Timestamp;

public class PersonVO {

    private int id; // private로 캡슐화 추천!
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private int age;
    private String address1;
    private Timestamp regDate;
    private Timestamp modifydate;

    // 기본 생성자
    public PersonVO() {
    }

    // 전체 필드 생성자
    public PersonVO(int id, String userId, String userPw, String userName, String userEmail,
            String phone1, String phone2, int age, String address1,
            Timestamp regDate, Timestamp modifydate) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.age = age;
        this.address1 = address1;
        this.regDate = regDate;
        this.modifydate = modifydate;
    }

    // getter/setter (필수!)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getModifydate() {
        return modifydate;
    }

    public void setModifydate(Timestamp modifydate) {
        this.modifydate = modifydate;
    }

    // phone 전체 보기 편하게
    public String getPhone() {
        return phone1 + "-" + phone2;
    }

    @Override
    public String toString() {
        return "PersonVO [id=" + id + ", userId=" + userId + ", userName=" + userName +
                ", phone=" + getPhone() + ", age=" + age + ", address1=" + address1 +
                ", regDate=" + regDate + ", modifydate=" + modifydate + "]";
    }
}