package java_jdbc.test.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class EmVO {
  private int id;
  private String name;
  private String salary;
  private int dept_id;
  private String hire_date;
}
