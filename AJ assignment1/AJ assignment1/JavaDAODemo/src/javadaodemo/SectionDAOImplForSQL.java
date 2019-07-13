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
public class SectionDAOImplForSQL implements SectionDataAccessObject {

    private Connection connection ;
    private PreparedStatement preparedInsertStatement;
    private PreparedStatement preparedGetByIdStatement;
    private PreparedStatement preparedGetAllStatement;
    private PreparedStatement preparedUpdateStatement;
    private PreparedStatement preparedDeleteByIdStatement;
    private PreparedStatement preparedDeleteAllStatement;
    public SectionDAOImplForSQL() {
        try {
            connection = DatabaseConnection.getConnection();
            
            /*InputStream inputStream = getClass().getResourceAsStream("sqlQueries.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);*/
            
//            FileReader used read only  txt file. But InputStreamReader used to read any types of file.
            FileReader fileReader = new FileReader("sqlQueries.properties");
 
            Properties properties = new Properties();
            properties.load(fileReader);
            
            preparedInsertStatement = connection.prepareStatement(properties.getProperty("INSERT_SECTION"));
            preparedGetByIdStatement = connection.prepareStatement(properties.getProperty("GET_SECTION_BY_SECID"));
            preparedGetAllStatement = connection.prepareStatement(properties.getProperty("GET_ALL_SECTION"));
            preparedDeleteByIdStatement = connection.prepareStatement(properties.getProperty("DELETE_SECTION_BY_SECID"));
            preparedUpdateStatement = connection.prepareStatement(properties.getProperty("UPDATE_SECTION"));
            preparedDeleteAllStatement = connection.prepareStatement(properties.getProperty("DELETE_ALL_SECTION"));
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Section insert(Section section) {        
        try {
            
            preparedInsertStatement.setInt(1, section.getSectionId());
            preparedInsertStatement.setInt(2, section.getSectionNumber());
            preparedInsertStatement.setInt(3, section.getSemester());
            preparedInsertStatement.setInt(4, section.getSeatLimit());
            preparedInsertStatement.setString(5, section.getCode());
            preparedInsertStatement.setString(6, section.getInitial());
            int effecteRow = preparedInsertStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getSectionbyID(section.getSectionId());
    }

    @Override
    public Section getSectionbyID(int id) {
        Section section = null;       
        try {
            //Statement statement = connection.createStatement();
            preparedGetByIdStatement.setInt(1, id);
            ResultSet resultset = preparedGetByIdStatement.executeQuery();
            while(resultset.next()){
                int secId = resultset.getInt("sectionid");
                int secNum = resultset.getInt("sectionnumber");
                int semester = resultset.getInt("semester");
                int seatLimit = resultset.getInt("seatlimit");
                String courseCode = resultset.getString("code");
                String teacherInitial = resultset.getString("initial");
                
                section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return section;
    }

    @Override
    public List<Section> getAllSection() {
        List<Section>sectionList = new ArrayList <>();
        try {            
            ResultSet resultset = preparedGetAllStatement.executeQuery();
            while(resultset.next()){
                int secId = resultset.getInt("sectionid");
                int secNum = resultset.getInt("sectionnumber");
                int semester = resultset.getInt("semester");
                int seatLimit = resultset.getInt("seatlimit");
                String courseCode = resultset.getString("code");
                String teacherInitial = resultset.getString("initial");
                
                Section section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                sectionList.add(section);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sectionList;
    }

    @Override
    public Section update(int id, Section section) {
        try {
            preparedInsertStatement.setInt(6, section.getSectionId());
            preparedInsertStatement.setInt(1, section.getSectionNumber());
            preparedInsertStatement.setInt(2, section.getSemester());
            preparedInsertStatement.setInt(3, section.getSeatLimit());
            preparedInsertStatement.setString(4, section.getCode());
            preparedInsertStatement.setString(5, section.getInitial());
            int effecteRow = preparedUpdateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getSectionbyID(id);
    }

    @Override
    public void delete(int id) {
       try {
            preparedDeleteByIdStatement.setInt(1, id);
            preparedDeleteByIdStatement.executeUpdate();
          } catch (SQLException ex) {
            Logger.getLogger(SectionDAOImplForSQL.class.getName()).log(Level.SEVERE, null, ex);
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
