package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest_40_Step1 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      String sql = "select name, id, format(salary,0), dept_id, date(hire_date) from emp_copy";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString(1);
        int id = rs.getInt(2);
        String salary = rs.getString(3);
        int dept_id = rs.getInt(4);
        String hire_date = rs.getString(5);

        System.out.printf("사번:%d, 이름:%s, 급여:$%s, 부서:%d, 입사일:%s\n", id, name, salary, dept_id,
            hire_date);
      }

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
    } finally {
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
