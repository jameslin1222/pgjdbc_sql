import java.sql.*;
import java.util.*;

public class a3_querytable_3_filter {
  public static void main(String[] args){
    try {
    String url = "jdbc:postgresql://localhost:5432/dvdrental";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","postgres");
    Connection conn = DriverManager.getConnection(url,props);

    Statement st = conn.createStatement();
    /* change below */
    ResultSet rs = st.executeQuery("SELECT * FROM city WHERE city_id < 5"); 
    /* change end */
    /* city column: city_id ,city ,country_id ,last_upda
te */
    ResultSetMetaData meta = rs.getMetaData();
    String colname1 = meta.getColumnName(1);
    String colname2 = meta.getColumnName(2);
    String colname3 = meta.getColumnName(3);
    String colname4 = meta.getColumnName(4);

    System.out.println(colname1 + "|" + colname2 + "|" + colname3 + "|" + colname4);
    while (rs.next()) {
      System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4));
    }
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
