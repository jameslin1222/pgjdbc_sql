// add begin
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
// add end

public class a4_db_properties {
/* Add Begin */
  public static Properties readProperties() {
    Properties props = new Properties();
    Path myPath = Paths.get("./database.properties");
    try {
      BufferedReader bf = Files.newBufferedReader(myPath,StandardCharsets.UTF_8);
      props.load(bf);
    } catch (IOException ex) {
      Logger.getLogger(a4_db_properties.class.getName()).log(Level.SEVERE, null, ex);
    }
    return props;
  }
/* Add end */

  public static void main(String[] args){
/* Add Begin */
    Properties props = readProperties();
    String url = props.getProperty("db.url");
    String user = props.getProperty("db.user");
    String passwd = props.getProperty("db.passwd"); 
/* Add end */
/* Change Begin */
    try {
    Connection conn = DriverManager.getConnection(url,user,passwd);

/* Change End */

    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT version()");

    if (rs.next()) {
      System.out.println(rs.getString(1));
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
