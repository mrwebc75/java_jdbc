package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest_20_Step2 {

  public static void main(String[] args) {

    Connection conn = null;
    Statement stmt = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);
      stmt = conn.createStatement();

      // maro 마로 1234 010-1234-5678 maro@daum.net now()
      String sql = "insert into member values ('" + args[0] + "', '" + args[1] + "', " + args[2]
          + ", '" + args[3] + "', '" + args[4] + "', now())";

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
