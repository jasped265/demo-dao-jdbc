/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
//import com.sun.jdi.connect.spi.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author jaspe
 */
public class Db {
    private static Connection conn = null;
    
    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }
        catch(IOException e){
            throw new DbException(e.getMessage());
        }
    }
    
    public static Connection getConnection(){
        if(conn == null){
            Properties prop = loadProperties();
            String url = prop.getProperty("dburl");
            String user = prop.getProperty("user");
            String passWord = prop.getProperty("password");
            try{
                 conn = DriverManager.getConnection(url, user, passWord);
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    public static Connection closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage()); 
            }
        }
    }
    
    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage()); 
            }
        }
    }
}
