package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest_10_Step2 {

  public static void main(String[] args) {
    
    Connection conn = null;//커넥션 객체변수
    
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "mvcweb","1234");
      
      //여러가지
      //자바 프로그램 작동
      //결과출력 .....하는 코드들
      System.out.println("JDBC 드라이버 연결성공~!");
      
      
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 등록여부를 확인하세요");
      
    } catch (SQLException e) {
      System.out.println("DB 연결정보를 확인하세요.");

    } finally {
      
      if(conn!=null) {
        try {conn.close();} catch (SQLException e) {} //커넥션 객체 close() 
        System.out.println("JDBC 드라이버 연결해제 성공~!");
      }
    }
  }
}
