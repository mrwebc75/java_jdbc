package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java_jdbc.test.model.EmVO;

public class SelectTest_40_Step6 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    List<EmVO> list = new ArrayList<EmVO>();

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      Scanner sc = new Scanner(System.in);

      System.out.println("조회할 사원명을 입력하세요~!");
      String input = sc.nextLine();

      // emp_copy 모든 William 보다 급여가 더 높은 사원의 이름, 급여를 조회하시오.
      // EmVO 클래스를 설계(id, name, salary, dept_id, hire_date)하여 적용하시오.
      StringBuilder sql = new StringBuilder();

      sql.append("select id, name, format(salary,0), dept_id, date(hire_date) from emp_copy ");
      sql.append("where salary >all (select salary from emp_copy where name = ?)");

      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setString(1, input);

      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        list.add(new EmVO(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5)
        ));
      }
      
      for(EmVO vo : list) {
        System.out.println(vo.toString());
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
