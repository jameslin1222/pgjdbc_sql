import java.sql.*;
import java.util.*;

public class a0_mini {
  public static void main(String[] args){
    System.out.println("你好 世界");
    try {
    String url = "jdbc:postgresql://localhost:5432/dvdrental";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","postgres");
    Connection conn = DriverManager.getConnection(url,props);
    } catch (SQLException e) {}
     


  }
}
