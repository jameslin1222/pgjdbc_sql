import java.sql.*;
import java.util.*;

public class a1_sqlexception {
  public static void main(String[] args){
    try {
    String url = "jdbc:postgresql://localhost:5432/dvdrental";
    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","postgres");
    Connection conn = DriverManager.getConnection(url,props);

    /* add below */
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT version()");
    System.out.println("PostgreSQL Version is :" + rs.getString(0));
    rs.close();
    st.close();
    /* add end */
    } catch (SQLException e) {
    /* add below */     
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    /* add end */
    System.out.println("Connection Database is OK"); 
    }
  }
}
