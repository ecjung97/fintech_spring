package net.daum.vo;

public class MemberVO { // TBL_MEMBER8 table column명과 일치하는 변수명을 가진 데이터 저장빈 class

    private String userid;
    private String userpw;
    private String username;
    public String email;
    public String regdate;

    public String getUserid() {return userid;}
    public void setUserid(String userid) {this.userid = userid;}
    public String getUserpw() {return userpw;}
    public void setUserpw(String userpw) {this.userpw = userpw;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getRegdate() {return regdate;}
    public void setRegdate(String regdate) {this.regdate = regdate;}
}
