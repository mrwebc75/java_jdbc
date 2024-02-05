package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest_20_Step3 {

  public static void main(String[] args) {

    Connection conn = null;
    Statement stmt = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);
      stmt = conn.createStatement();

      // id 제약조건을 어기는 데이터 입력 테스트 시도 - id4 하니 0000 010-8282-8282 hani@naver.com
      // phone 제약조건을 어기는 데이터 입력 테스트 시도 - douner 도우너 0000 010-1234-5678 douner@naver.com
      // 올바른 데이터 입력 테스트 시도 - pororo 뽀로로 0000 010-5656-5656 pororo@naver.com
      String sql = "insert into c_member values ('" + args[0] + "', '" + args[1] + "', " + args[2]
          + ", '" + args[3] + "', '" + args[4] + "',now())";

      int rowcount = stmt.executeUpdate(sql);
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
