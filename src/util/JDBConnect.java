package util;

import javax.servlet.ServletContext;
import java.sql.*;

//Written  by 여명
public class JDBConnect {
    public Connection con;
    public Statement stmt;
    public PreparedStatement psmt;

    public CallableStatement cstmt;
    public ResultSet rs;

    // 기본 생성자
    public JDBConnect() {
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // DB에 연결
            String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
            String id = "ace";
            String pwd = "me";
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(기본 생성자)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            //로그인 -> 로그아웃 -> 로그인하면 이게 싱글톤이라 con이 영영 닫혀버려서 con은 안닫게 해놨음
            //추후 커넥션풀 사용할때 전체적으로 조정하자
//            if (con != null) con.close();

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}