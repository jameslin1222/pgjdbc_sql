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
/* add start */
import java.io.File;
/* add end */

public class a7_insert_image {
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
    String query = "INSERT INTO images(id,data) VALUES(?,?)";
    int id = 1;
    File img = new File("res/2233.jpg");
    try (FileInputStream fin = new FileInputStream(img)){
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1,id);
    pst.setBinaryStream(2, fin, (int) img.length());
    pst.executeUpdate();
    System.out.println("INSERT images is OK!");
    } catch (IOException e) {
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
 

    }
/* Change End */
    } catch (SQLException e) {
    e.printStackTrace();
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.exit(1);
    }
  }
}
