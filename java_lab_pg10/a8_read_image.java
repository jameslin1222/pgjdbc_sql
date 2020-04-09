import java.io.BufferedReader;
//import java.io.FileInputStream;
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
import java.io.File;
/* add being */
import java.io.FileOutputStream;
/* add end */

public class a8_read_image {
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
/* create table images (id int primary key,data bytea); */
    String query = "SELECT data,length(data) FROM images WHERE id = 1";
    File myFile = new File("res/2233_out.jpg");
    PreparedStatement pst = conn.prepareStatement(query);
    ResultSet rs = pst.executeQuery();
    rs.next();
    try (FileOutputStream fos = new FileOutputStream(myFile)){
      int len = rs.getInt(2);
      byte[] buf = rs.getBytes("data");
      fos.write(buf, 0, len);
    } catch (IOException e) {
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    System.out.println("Read image is OK!"); 

    }
/* Change End */
    } catch (SQLException e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    }
  }
}
