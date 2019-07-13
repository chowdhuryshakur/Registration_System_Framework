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
public class FacultyDAOImplForSQL implements FacultyDataAccessObject {

    private Connection connection ;
    private PreparedStatement preparedInsertStatement;
    private PreparedStatement preparedGetByIdStatement;
    private PreparedStatement preparedGetAllStatement;
    private PreparedStatement preparedUpdateStatement;
    private PreparedStatement preparedDeleteByIdStatement;
    private PreparedStatement preparedDeleteAllStatement;
    public FacultyDAOImplForSQL() {
        try {
            connection = DatabaseConnection.getConnection();
            
            /*InputStream inputStream = getClass().getResourceAsStream("sqlQueries.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);*/
            
//            FileReader used read only  txt file. But InputStreamReader used to read any types of file.
            FileReader fileReader = new FileReader("sqlQueries.properties");
 
            Properties properties = new Properties();
            properties.load(fileReader);
            
            preparedInsertStatement = connection.prepareStatement(properties.getProperty("INSERT_FACULTY"));
            preparedGetByIdStatement = connection.prepareStatement(properties.getProperty("GET_FACULTY_BY_INITIAL"));
            preparedGetAllStatement = connection.prepareStatement(properties.getProperty("GET_ALL_FACULTY"));
            preparedDeleteByIdStatement = connection.prepareStatement(properties.getProperty("DELETE_FACULTY_BY_INITIAL"));
            preparedUpdateStatement = connection.prepareStatement(properties.getProperty("UPDATE_FACULTY"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_FACULTY"));
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Faculty insert(Faculty faculty) {        
        try {
            
            preparedInsertStatement.setString(1, faculty.getInitial());
            preparedInsertStatement.setString(2, faculty.getName());
            preparedInsertStatement.setString(3, faculty.getRank());
            int effecteRow = preparedInsertStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getFacultybyInitial(faculty.getInitial());
    }

    @Override
    public Faculty getFacultybyInitial(String initial) {
        Faculty faculty = null;       
        try {
            //Statement statement = connection.createStatement();
            preparedGetByIdStatement.setString(1, initial);
            ResultSet resultset = preparedGetByIdStatement.executeQuery();
            while(resultset.next()){
                String Initial = resultset.getString("initial");
                String name = resultset.getString("name");
                String rank = resultset.getString("rank");
                
                faculty = new Faculty(Initial, name, rank);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculty;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        List<Faculty>facultyList = new ArrayList <>();
        try {            
            ResultSet resultset = preparedGetAllStatement.executeQuery();
            while(resultset.next()){
                String Initial = resultset.getString("initial");
                String name = resultset.getString("name");
                String rank = resultset.getString("rank");
                
                Faculty faculty = new Faculty(Initial, name, rank);
                facultyList.add(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facultyList;
    }

    @Override
    public Faculty update(String Initial, Faculty faculty) {
        try { 
            preparedInsertStatement.setString(1, faculty.getName());
            preparedInsertStatement.setString(2, faculty.getRank());
            preparedInsertStatement.setString(3, faculty.getInitial());
            int effecteRow = preparedUpdateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getFacultybyInitial(Initial);
    }

    @Override
    public void delete(String Initial) {
       try {
            preparedDeleteByIdStatement.setString(1, Initial);
            preparedDeleteByIdStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
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
