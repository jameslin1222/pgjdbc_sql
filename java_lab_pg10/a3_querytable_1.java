import java.sql.*;
import java.util.*;

public class a3_querytable_1 {
  public static void main(String[] args){
    try {
    String url = "jdbc:postgresql://localhost:5432/dvdrental";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","postgres");
    Connection conn = DriverManager.getConnection(url,props);

    /* change below */
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("TABLE city"); // SELECT * FROM city
    /* city column: city_id ,city ,country_id ,last_upda
te */
    while (rs.next()) {
      System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));
    }
   /* change end */
    rs.close();
    st.close();
    } catch (SQLException e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    System.out.println("Connection Database is OK"); 
    }
  }
}
