package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest_40_Step4 {
  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      Scanner sc = new Scanner(System.in);

      System.out.println("조회할 도시명을 입력하세요~!");
      String input = sc.nextLine();

      // 도시명을 입력받아 해당 도시(london)에 근무하는 사원명 부서명 도시명 조회하시오.
      StringBuilder sql = new StringBuilder();
      sql.append("select first_name, department_name, city from employees e ");
      sql.append("inner join departments d on e.department_id = d.department_id ");
      sql.append("inner join locations L on L.location_id = d.location_id ");
      sql.append("where L.city = ?");

      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setString(1, input);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString(1);
        String deptName = rs.getString(2);
        String city = rs.getString(3);

        System.out.printf("사원명:%s, 부서명:%s, 도시명:%s\n", name, deptName, city);
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
