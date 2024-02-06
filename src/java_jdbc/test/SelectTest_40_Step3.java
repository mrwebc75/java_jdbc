package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest_40_Step3 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      // emp_copy 테이블에서 전체사원의 급여 총합을 소수점 두자리까지 출력하시오
      String sql = "select sum(salary) as 'totSalary' from emp_copy";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      rs.next();
      Double totSalary = rs.getDouble("totSalary");

      System.out.printf("전체사원의 급여 총합 : %.2f", totSalary);

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
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
