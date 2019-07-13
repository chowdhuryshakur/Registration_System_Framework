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
public class CourseDAOImplForSQL implements CourseDataAccessObject {

    private Connection connection ;
    private PreparedStatement preparedInsertStatement;
    private PreparedStatement preparedGetByIdStatement;
    private PreparedStatement preparedGetAllStatement;
    private PreparedStatement preparedUpdateStatement;
    private PreparedStatement preparedDeleteByIdStatement;
    private PreparedStatement preparedDeleteAllStatement;
    public CourseDAOImplForSQL() {
        try {
            connection = DatabaseConnection.getConnection();
            
            /*InputStream inputStream = getClass().getResourceAsStream("sqlQueries.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);*/
            
//            FileReader used read only  txt file. But InputStreamReader used to read any types of file.
            FileReader fileReader = new FileReader("sqlQueries.properties");
 
            Properties properties = new Properties();
            properties.load(fileReader);
            
            preparedInsertStatement = connection.prepareStatement(properties.getProperty("INSERT_COURSE"));
            preparedGetByIdStatement = connection.prepareStatement(properties.getProperty("GET_COURSE_BY_CODE"));
            preparedGetAllStatement = connection.prepareStatement(properties.getProperty("GET_ALL_COURSE"));
            preparedDeleteByIdStatement = connection.prepareStatement(properties.getProperty("DELETE_COURSE_BY_CODE"));
            preparedUpdateStatement = connection.prepareStatement(properties.getProperty("UPDATE_COURSE"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_COURSE"));
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Course insert(Course course) {        
        try {
            
            preparedInsertStatement.setString(1, course.getCode());
            preparedInsertStatement.setString(2, course.getTitle());
            preparedInsertStatement.setDouble(3, course.getCredit());
            int effecteRow = preparedInsertStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getCoursebyCode(course.getCode());
    }

    @Override
    public Course getCoursebyCode(String code) {
        Course course = null;       
        try {
            //Statement statement = connection.createStatement();
            preparedGetByIdStatement.setString(1, code);
            ResultSet resultset = preparedGetByIdStatement.executeQuery();
            while(resultset.next()){
                String courseCode = resultset.getString("code");
                String title = resultset.getString("title");
                double credit = resultset.getDouble("credit");
                
                course = new Course(courseCode, title, credit);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course>courseList = new ArrayList <>();
        try {            
            ResultSet resultset = preparedGetAllStatement.executeQuery();
            while(resultset.next()){
                String courseCode = resultset.getString("code");
                String title = resultset.getString("title");
                double credit = resultset.getDouble("credit");
                
                Course course = new Course(courseCode, title, credit);
                courseList.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList;
    }

    @Override
    public Course update(String code, Course course) {
        try {
            preparedUpdateStatement.setString(3, course.getCode());
            preparedUpdateStatement.setString(1, course.getTitle());
            preparedUpdateStatement.setDouble(2, course.getCredit());
            int effecteRow = preparedUpdateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getCoursebyCode(code);
    }

    @Override
    public void delete(String code) {
       try {
            preparedDeleteByIdStatement.setString(1, code);
            preparedDeleteByIdStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
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
