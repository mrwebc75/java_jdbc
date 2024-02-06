package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java_jdbc.test.model.EmDTO;

public class SelectTest_40_Step5 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      Scanner sc = new Scanner(System.in);

      System.out.println("조회할 사원명을 입력하세요~!");
      String input = sc.nextLine();

      // William 보다 급여가 더 높은 사원들의 이름, 급여를 조회하시오.
      // 입력받은 사원명 보다 급여가 더 높은 사원들의 이름, 급여를 조회하시오.
      StringBuilder sql = new StringBuilder();
      sql.append("select first_name, format(salary,0) from employees ");
      sql.append("where salary >any (select salary from employees where first_name = ?)");

      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setString(1, input);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        EmDTO dto = new EmDTO(rs.getString(1), rs.getString(2));
        System.out.println(dto.toString());
      }

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        rs.close();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }

      try {
        pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }
}
