/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

/**
 *
 * @author shakur
 */

import com.sun.media.jfxmediaimpl.platform.Platform;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnection {
    private static Connection connection = null;
    private static DatabaseConnection instance = new DatabaseConnection();
    
    private DatabaseConnection() {
        
        try {
            FileReader fileReader = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                String url = properties.getProperty("URL");
            // TODO replace the hardcoded URL with something you read from the properties
            connection = DriverManager.getConnection(url, username, password);
            } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static Connection getConnection() {
        return connection;
    }

}
