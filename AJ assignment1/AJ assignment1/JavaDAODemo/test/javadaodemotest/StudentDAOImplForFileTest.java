/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemotest;

import javadaodemo.Course;
import javadaodemo.CourseDAOImplForFile;
import javadaodemo.CourseDataAccessObject;
import javadaodemo.Student;
import javadaodemo.StudentDAOImplForFile;
import javadaodemo.StudentDataAccessObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class StudentDAOImplForFileTest {
    private static StudentDataAccessObject studentDataAccessObject;
    private Student student = new Student("111", "shakur", "c@yy");
    private Student student1 = new Student("111", "shakur","s@yy");
    public StudentDAOImplForFileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    studentDataAccessObject = new StudentDAOImplForFile();
    }
    
    @AfterClass
    public static void tearDownClass() {
        studentDataAccessObject = null;
    }
    
    @Before
    public void setUp() {
        studentDataAccessObject.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
 
    @Test
    public void testInsert() {  
        Student createdStudent = studentDataAccessObject.insert(student);       
        assertEquals(student, createdStudent);
    }
    @Test
    public void testGetStudentByID() {  
        studentDataAccessObject.insert(student);  
        assertEquals(student, studentDataAccessObject.getStduentbyID(student.getId()));
    }
    @Test
    public void testUpdate() {  
        studentDataAccessObject.insert(student);
        studentDataAccessObject.update(student.getId(),student1);
        
        assertEquals(student1, studentDataAccessObject.getStduentbyID(student.getId()));
    }
    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
