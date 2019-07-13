/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemotest;

import javadaodemo.Faculty;
import javadaodemo.FacultyDAOImplForSQL;
import javadaodemo.FacultyDataAccessObject;
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
public class FacultyDAOImplForSQLTest {
    private static FacultyDataAccessObject facultyDataAccessObject;
   private Faculty faculty = new Faculty("KMH", "Tamal","Seniot lecturer");
    private Faculty faculty1 = new Faculty("KMH", "Tamal","Assistant prf");
    public FacultyDAOImplForSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        facultyDataAccessObject = new FacultyDAOImplForSQL();
    }
    
    @AfterClass
    public static void tearDownClass() {
        facultyDataAccessObject = null;
    }
    
    @Before
    public void setUp() {
        facultyDataAccessObject.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInsert() {  
        Faculty createdFaculty = facultyDataAccessObject.insert(faculty);       
        assertEquals(faculty, createdFaculty);
    }
    @Test
    public void testGetCourseByCode() {  
        facultyDataAccessObject.insert(faculty);
        assertEquals(faculty, facultyDataAccessObject.getFacultybyInitial(faculty.getInitial()));
    }
    @Test
    public void testUpdate() {  
        facultyDataAccessObject.insert(faculty);
        facultyDataAccessObject.update(faculty.getInitial(),faculty1);
        
        assertEquals(faculty1, facultyDataAccessObject.getFacultybyInitial(faculty.getInitial()));
    }
   
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
