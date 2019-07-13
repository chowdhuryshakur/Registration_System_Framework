/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemotest;

import javadaodemo.Course;
import javadaodemo.CourseDAOImplForSQL;
import javadaodemo.CourseDataAccessObject;
import javadaodemo.Registration;
import javadaodemo.RegistrationDAOImplForSQL;
import javadaodemo.RegistrationDataAccessObject;
import javadaodemo.Section;
import javadaodemo.Student;
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
public class RegistrationDAOImplForSQLTest {
    private static RegistrationDataAccessObject registrationDataAccessObject;
   private Registration registration = new Registration("111", 11);
    private Registration registration1 = new Registration("111", 12);
     
    public RegistrationDAOImplForSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        registrationDataAccessObject = new RegistrationDAOImplForSQL();
    }
    
    @AfterClass
    public static void tearDownClass() {
        registrationDataAccessObject = null;
    }
    
    @Before
    public void setUp() {
        registrationDataAccessObject.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInsert() {  
        
        Registration createdRegistration = registrationDataAccessObject.insert(registration);       
        assertEquals(registration, createdRegistration);
    }
    @Test
    public void testGetRegistrationByIdAndSub() {    
        assertEquals(registration, registrationDataAccessObject.getRegistrationbyIDAndSub(registration.getId(), registration.getSectionId()));
    }
    @Test
    public void testUpdate() { 
        registrationDataAccessObject.update(registration.getId(), registration.getSectionId(),registration1);
        
        assertEquals(registration1, registrationDataAccessObject.getRegistrationbyIDAndSub(registration.getId(), registration.getSectionId()));
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
