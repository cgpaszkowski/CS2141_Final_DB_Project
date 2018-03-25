package finalgroupproject.cs.pkg2141;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinalGroupProjectCS2141 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_project?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        FinalGroupProjectCS2141 dbProject = new FinalGroupProjectCS2141();
        dbProject.createConnection();
    }
    
    void createConnection(){
        try {
            Class.forName(JDBC_DRIVER);  
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM artist");
            while(rs.next()){
                String name = rs.getString("Artist_Name");
                System.out.println(name);
            }
            
            System.out.println("Database Connection Successful");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalGroupProjectCS2141.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(FinalGroupProjectCS2141.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
