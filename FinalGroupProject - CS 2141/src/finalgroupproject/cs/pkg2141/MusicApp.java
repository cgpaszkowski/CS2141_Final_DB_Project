
package finalgroupproject.cs.pkg2141;

import com.mysql.jdbc.Connection;
import static finalgroupproject.cs.pkg2141.GUI.DB_URL;
import java.sql.DriverManager;

public class MusicApp extends javax.swing.JFrame {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_project?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    public MusicApp(){
        initComponents();
    }
    
    public Connection getConnection(){
        Connection con;
        
        try {
            con = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return con;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
