import java.sql.*;
import java.util.*;

public class a2_version {
  public static void main(String[] args){
    try {
    String url = "jdbc:postgresql://localhost:5432/dvdrental";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","postgres");
    Connection conn = DriverManager.getConnection(url,props);

    /* change below */
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT version()");

    if (rs.next()) {
      System.out.println(rs.getString(1));
    }
    rs.close();
    st.close();
    /* change end */
    } catch (SQLException e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    System.out.println("Connection Database is OK"); 
    }
  }
}
