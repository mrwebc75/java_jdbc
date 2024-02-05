package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest_20_Step1 {

  public static void main(String[] args) {

    // 실행할 쿼리
    String sql = "";
    StringBuilder sb = new StringBuilder();
    sb.append("insert into member values ");
    sb.append("('hani','하니',5555,'010-7294-3724','hani@a.com',now())");
    sql = sb.toString();

    Connection conn = null;
    Statement stmt = null;

    try {

      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);
      stmt = conn.createStatement();// 쿼리문을 전송해서 실행시키는 객체
      int rowcount = stmt.executeUpdate(sql);

      System.out.println("회원가입행 개수 = " + rowcount);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
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
}// class
