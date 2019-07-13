/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemotest;

import javadaodemo.Course;
import javadaodemo.CourseDAOImplForSQL;
import javadaodemo.CourseDataAccessObject;
import javadaodemo.Section;
import javadaodemo.SectionDAOImplForSQL;
import javadaodemo.SectionDataAccessObject;
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
public class SectionDAOImplForSQLTest {
    private static SectionDataAccessObject sectionDataAccessObject;
   private Section section = new Section(1,1,42,28,"CSE2012", "KMH");
    private Section section1 = new Section(1,1,45,30,"CSE2012", "SM");
    public SectionDAOImplForSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sectionDataAccessObject = new SectionDAOImplForSQL();
    }
    
    @AfterClass
    public static void tearDownClass() {
        sectionDataAccessObject = null;
    }
    
    @Before
    public void setUp() {
        sectionDataAccessObject.deleteAll();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInsert() {  
        Section createdSection = sectionDataAccessObject.insert(section);       
        assertEquals(section, createdSection);
    }
    @Test
    public void testGetSectionBySecId() {    
        assertEquals(section, sectionDataAccessObject.getSectionbyID(section.getSectionId()));
    }
    @Test
    public void testUpdate() {  
        sectionDataAccessObject.update(section.getSectionId(),section1);
        
        assertEquals(section1, sectionDataAccessObject.getSectionbyID(section.getSectionId()));
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
