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
public class StudentDAOImplForSQL implements StudentDataAccessObject {

    private Connection connection ;
    private PreparedStatement preparedInsertStatement;
    private PreparedStatement preparedGetByIdStatement;
    private PreparedStatement preparedGetAllStatement;
    private PreparedStatement preparedUpdateStatement;
    private PreparedStatement preparedDeleteByIdStatement;
    private PreparedStatement preparedDeleteAllStatement;
   
    public StudentDAOImplForSQL() {
        try {
            connection = DatabaseConnection.getConnection();
            
            /*InputStream inputStream = getClass().getResourceAsStream("sqlQueries.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);*/
            
//            FileReader used read only  txt file. But InputStreamReader used to read any types of file.
            FileReader fileReader = new FileReader("sqlQueries.properties");
 
            Properties properties = new Properties();
            properties.load(fileReader);
            
            preparedInsertStatement = connection.prepareStatement(properties.getProperty("INSERT_STUDENT"));
            preparedGetByIdStatement = connection.prepareStatement(properties.getProperty("GET_STUDENT_BY_ID"));
            preparedGetAllStatement = connection.prepareStatement(properties.getProperty("GET_ALL_STUDENTS"));
            preparedDeleteByIdStatement = connection.prepareStatement(properties.getProperty("DELETE_STUDENTS_BY_ID"));
            preparedUpdateStatement = connection.prepareStatement(properties.getProperty("UPDATE_STUDENT"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_STUDENT"));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Student insert(Student student) {        
        try {
/*  use this for without prepared statement  Statement statement = connection.createStatement();
                                             statement.executeUpdate("insert into student values('"+student.getId()+"','"+student.getName()+"','"+student.getMail()+"');");*/           
            preparedInsertStatement.setString(1, student.getId());
            preparedInsertStatement.setString(2, student.getName());
            preparedInsertStatement.setString(3, student.getMail());
            int effecteRow = preparedInsertStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getStduentbyID(student.getId());
    }

    @Override
    public Student getStduentbyID(String Id) {
        Student student = null;       
        try {
            //Statement statement = connection.createStatement();
            preparedGetByIdStatement.setString(1, Id);
            ResultSet resultset = preparedGetByIdStatement.executeQuery();
            while(resultset.next()){
                String studentId = resultset.getString("id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                
                student = new Student(studentId, name, email);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student>studentList = new ArrayList <>();
        try {            
            ResultSet resultset = preparedGetAllStatement.executeQuery();
            while(resultset.next()){
                String studentId = resultset.getString("id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                
                Student student = new Student(studentId, name, email);
                studentList.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    @Override
    public Student update(String Id, Student student) {
        try {
            preparedUpdateStatement.setString(1, student.getName());
            preparedUpdateStatement.setString(2, student.getMail());
            preparedUpdateStatement.setString(3, student.getId());
            int effecteRow = preparedUpdateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getStduentbyID(Id);
    }

    @Override
    public void delete(String Id) {
       try {
            preparedDeleteByIdStatement.setString(1, Id);
            preparedDeleteByIdStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
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
