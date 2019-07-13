/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shakur
 */
public class RegistrationDAOImplForSQL implements RegistrationDataAccessObject {

    private Connection connection ;
    private PreparedStatement preparedInsertStatement;
    private PreparedStatement preparedGetByIdStatement;
    private PreparedStatement preparedGetAllStatement;
    private PreparedStatement preparedUpdateStatement;
    private PreparedStatement preparedDeleteByIdStatement;
    private PreparedStatement preparedDeleteAllStatement;
   
    public RegistrationDAOImplForSQL() {
        try {
            connection = DatabaseConnection.getConnection();
            
            /*InputStream inputStream = getClass().getResourceAsStream("sqlQueries.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);*/
            
//            FileReader used read only  txt file. But InputStreamReader used to read any types of file.
            FileReader fileReader = new FileReader("sqlQueries.properties");
 
            Properties properties = new Properties();
            properties.load(fileReader);
            
            preparedInsertStatement = connection.prepareStatement(properties.getProperty("INSERT_REGISTRATION"));
            preparedGetByIdStatement = connection.prepareStatement(properties.getProperty("GET_REGISTRATION_BY_ID_SUB"));
            preparedGetAllStatement = connection.prepareStatement(properties.getProperty("GET_ALL_REGISTRATION"));
            preparedDeleteByIdStatement = connection.prepareStatement(properties.getProperty("DELETE_REGISTRATION_BY_ID_SUB"));
            preparedUpdateStatement = connection.prepareStatement(properties.getProperty("UPDATE_REGISTRATION"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_REGISTRATION"));
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Registration insert(Registration registration) {        
        try {
            
            preparedInsertStatement.setString(1, registration.getId());
            preparedInsertStatement.setInt(2, registration.getSectionId());
            int effecteRow = preparedInsertStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getRegistrationbyIDAndSub(registration.getId(),registration.getSectionId());
    }

    @Override
    public Registration getRegistrationbyIDAndSub(String studentId, int secId) {
        Registration registration = null;       
        try {
            //Statement statement = connection.createStatement();
            preparedGetByIdStatement.setString(1, studentId);
            preparedGetByIdStatement.setInt(2, secId);
            ResultSet resultset = preparedGetByIdStatement.executeQuery();
            while(resultset.next()){
                String stuId = resultset.getString("id");
                int secID = resultset.getInt("sectionid");
                
                registration = new Registration(stuId, secID);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registration;
    }

    @Override
    public List<Registration> getAllRegistration() {
        List<Registration>registrationList = new ArrayList <>();
        try {            
            ResultSet resultset = preparedGetAllStatement.executeQuery();
            while(resultset.next()){
                String stuId = resultset.getString("id");
                int secID = resultset.getInt("sectionid");
                
                Registration registration = new Registration(stuId, secID);
                registrationList.add(registration);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrationList;
    }

    @Override
    public Registration update(String id, int secId, Registration registration) {
        try {
             preparedInsertStatement.setString(1, registration.getId());
            preparedInsertStatement.setInt(2, registration.getSectionId());
            preparedInsertStatement.setString(3, id);
            preparedInsertStatement.setInt(4, secId);
            int effecteRow = preparedUpdateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getRegistrationbyIDAndSub(id, secId);
    }

    @Override
    public void delete(String id, int secId) {
       try {
            preparedDeleteByIdStatement.setString(1, id);
            preparedDeleteByIdStatement.setInt(2, secId);
            preparedDeleteByIdStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteAll() {
         try {
            preparedDeleteAllStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
