package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest_10_Step1 {

  public static void main(String[] args) {

    try {

      // 1. mysql-connector.jar 파일을 사용할 수 있도록 로드
      // JDBC 드라이버 클래스를 사용할 수 있도록 메모리상으로 로딩
      // JDBC 드라이버란? DB와 자바프로그램 사이에서 데이터를 처리하는 역할의 API를 가진 클래스
      Class.forName("com.mysql.cj.jdbc.Driver");

      // 2. 커넥션 객체 얻어오기 - DB와 연결을 맺는 단계
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "mvcweb",
          "1234");
      System.out.println("JDBC 드라이버 연결성공~!");

      conn.close();

    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 등록여부를 확인하세요");

    } catch (SQLException e) {
      System.out.println("DB 연결정보를 확인하세요.");
    }
  }
}
