package java_jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTest_30 {

  public static void main(String[] args) {

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      Class.forName(DBinfo.driver);
      conn = DriverManager.getConnection(DBinfo.url, DBinfo.id, DBinfo.pwd);

      Scanner sc = new Scanner(System.in);
      System.out.println("수정할 사원의 급여, 부서번호, 아이디를 순서대로 입력하세요");
      String[] cmd = sc.nextLine().split(" ");

      // 키보드로 수정할 사원의 급여, 부서번호, 아이디를 입력받아 처리
      // 아이디가 308번인 사원의 급여(30000)와 부서번호(20)를 수정
      String sql = "update emp_copy set salary=?, dept_id=? where id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setDouble(1, Double.parseDouble(cmd[0]));
      pstmt.setInt(2, Integer.parseInt(cmd[1]));
      pstmt.setInt(3, Integer.parseInt(cmd[2]));

      int rowcount = pstmt.executeUpdate();

      System.out.println("수정행 개수 = " + rowcount);

      sc.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
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

  }// main
}
