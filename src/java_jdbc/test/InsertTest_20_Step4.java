package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest_20_Step4 {
  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      // lila 리라 0000 010-7294-5656 lila@naver.com
      String sql = "insert into c_member values (?,?,?,?,?,now())";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, args[0]);
      pstmt.setString(2, args[1]);
      pstmt.setInt(3, Integer.valueOf(args[2]));
      pstmt.setString(4, args[3]);
      pstmt.setString(5, args[4]);

      int rowcount = pstmt.executeUpdate();
      System.out.println("회원가입행 개수 = " + rowcount);

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }// main
}
