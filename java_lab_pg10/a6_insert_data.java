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

public class a6_insert_data {
  public static Properties readProperties() {
    Properties props = new Properties();
    Path myPath = Paths.get("./database.properties");
    try {
      BufferedReader bf = Files.newBufferedReader(myPath,StandardCharsets.UTF_8);
      props.load(bf);
    } catch (IOException ex) {
      Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex); 
    }
    return props;
  }

  public static void main(String[] args){
    Properties props = readProperties();
    String url = props.getProperty("db.url");
    String user = props.getProperty("db.user");
    String passwd = props.getProperty("db.passwd"); 
    try {
    Connection conn = DriverManager.getConnection(url,user,passwd);

/* Change Begin */
    int id = 100;
    String name = "James";
    String query = "INSERT INTO t1(id,name) VALUES(?,?)";
    PreparedStatement pst = conn.prepareStatement(query);

    pst.setInt(1,id);
    pst.setString(2,name);
    pst.executeUpdate();
    System.out.println("INSERT TABLE t1 is OK. ID is " + id + " ,NAME is " + name );
/* Change End */
    } catch (SQLException e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    }
  }
}
