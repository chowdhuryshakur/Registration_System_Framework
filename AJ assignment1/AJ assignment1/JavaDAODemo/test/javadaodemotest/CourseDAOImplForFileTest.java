/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemotest;

import javadaodemo.Course;
import javadaodemo.CourseDAOImplForFile;
import javadaodemo.CourseDataAccessObject;
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
public class CourseDAOImplForFileTest {
    private static CourseDataAccessObject courseDataAccessObject;
    private Course course = new Course("111", "Java",1.0);
    private Course course1 = new Course("111", "C++",1.0);
    public CourseDAOImplForFileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    courseDataAccessObject = new CourseDAOImplForFile();
    }
    
    @AfterClass
    public static void tearDownClass() {
        courseDataAccessObject = null;
    }
    
    @Before
    public void setUp() {
        courseDataAccessObject.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
 
    @Test
    public void testInsert() {  
        Course createdCourse = courseDataAccessObject.insert(course);       
        assertEquals(course, createdCourse);
    }
    @Test
    public void testGetCourseByCode() {  
        courseDataAccessObject.insert(course);  
        assertEquals(course, courseDataAccessObject.getCoursebyCode(course.getCode()));
    }
    @Test
    public void testUpdate() {  
        courseDataAccessObject.insert(course);
        courseDataAccessObject.update(course.getCode(),course1);
        
        assertEquals(course1, courseDataAccessObject.getCoursebyCode(course.getCode()));
    }
    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
