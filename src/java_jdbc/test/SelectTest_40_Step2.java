package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest_40_Step2 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      // emp_copy 테이블에서 50번부서의 5000<=급여<=10000 인 사원의 이름, 급여, 부서코드, 입사일 정보를 조회
      String sql = "select name, format(salary,0), dept_id, date_format(hire_date,'%Y/%m/%d') "
          + "from emp_copy where dept_id=? and salary between ? and ?";

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 50);
      pstmt.setDouble(2, 5000);
      pstmt.setDouble(3, 10000);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString(1);
        String salary = rs.getString(2);
        int dept_id = rs.getInt(3);
        String hire_date = rs.getString(4);

        System.out.printf("이름:%s, 급여:$%s, 부서:%d, 입사일:%s\n", name, salary, dept_id, hire_date);
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
