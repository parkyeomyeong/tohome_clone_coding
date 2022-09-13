package com.tohome.dao;

import com.tohome.dto.MemberDTO;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import util.JDBConnect;

import java.lang.reflect.Member;

//Written  by 여명
public class MemberDAO extends JDBConnect{
    // ----- Singlton Patten -----
    private MemberDAO() {
        // 부모Class인 JDBCConnect의 생성자 호출로 db연결객체 생성
        super();
    }
    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance() {
        return instance;
    }
    // ----- Singlton Patten -----


    // 회원가입 가능한 id인지 체크
    public int checkID(String uid){
        String query = "{call member_pkg.id_check(?,?)}";
        int result = 0;

        try{
            cstmt = con.prepareCall(query); // 동적 쿼리문 준비
            cstmt.setString(1, uid);
            cstmt.registerOutParameter(2, OracleType.NUMBER);

            cstmt.executeQuery();
            result = cstmt.getInt(2);

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public MemberDTO memberJoin(
            String userId,
            String userPwd,
            String userName,
            String bithYMD,
            String mobileNum,
            String gender,
            String addressMain,
            String addressDetail,
            String addressName )
        {
        MemberDTO dto = new MemberDTO();  // 회원 정보 DTO 객체 생성
        String query = "{call member_pkg.member_join(?,?,?,?,?,?,?,?,?)}";  // 쿼리문 템플릿
        try{
            // 쿼리 실행
            cstmt = con.prepareCall(query); // 동적 쿼리문 준비
            cstmt.setString(1, userId);
            cstmt.setString(2, userPwd);
            cstmt.setString(3, userName);
            cstmt.setString(4, bithYMD);
            cstmt.setString(5, mobileNum);
            cstmt.setString(6, gender);
            cstmt.setString(7, addressMain);
            cstmt.setString(8, addressDetail);
            cstmt.setString(9, addressName);


            // CallableStatement를 실행
            int cnt =cstmt.executeUpdate();

            //성공하면 세션을 위한 id, name넣기 ~
            if (cnt > 0){
                dto.setUser_id(userId);
                dto.setUser_name(userName);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    // 명시한 아이디/패스워드와 일치하는 회원 정보를 반환합니다.
    public MemberDTO getLoginMemberDTO(String uid, String pwd) {
        MemberDTO dto = new MemberDTO();  // 회원 정보 DTO 객체 생성
        String query = "{call member_pkg.member_login(?,?,?)}";  // 쿼리문 템플릿

        try {
            // 쿼리 실행
            cstmt = con.prepareCall(query); // 동적 쿼리문 준비
            cstmt.setString(1, uid);
            cstmt.setString(2, pwd);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            //OracleTypes.CURSOR를 사용할려면 ojdbc8.jar 안에있는 거라 라이브러리 추가 같은 세팅 해야함

            // CallableStatement를 실행
            cstmt.executeQuery();

            // getCursor() 메서드를 사용하기 위해 OracleCallableStatement Object로 변환
            // CallableStatement는 getCursor() 메서드가 정의되어 있지 않기 때문
            OracleCallableStatement ocstmt = (OracleCallableStatement)cstmt;
            // ResultSet에 결과 데이터를 담은 Cursor를 저장
            rs = ocstmt.getCursor(3);

            // 결과 처리
            if (rs.next()) {
                // 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
                dto.setUser_no(rs.getInt("user_no"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setPwd(rs.getString("pwd"));
                dto.setUser_name(rs.getString("user_name"));
                dto.setBirth_ymd(rs.getString("birth_ymd"));
                dto.setMobile_num(rs.getString("mobile_num"));
                dto.setGender(rs.getInt("gender"));
                dto.setGrade(rs.getString("grade"));
                dto.setSale_rate(rs.getInt("sale_rate"));
                dto.setBuy_sum(rs.getInt("buy_sum"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return dto;  // DTO 객체 반환
    }


}
