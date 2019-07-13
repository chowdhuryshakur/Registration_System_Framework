/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shakur
 */
public class CourseDAOImplForFile implements CourseDataAccessObject {

    @Override
    public Course insert(Course course) {
        try {
            RandomAccessFile output = new RandomAccessFile("course.txt","rw");
            String s = ""+course.getCode()+";"+course.getTitle()+";"+course.getCredit()+"";
            long existingLength = output.length();
          output.seek(existingLength);
            output.writeBytes(s+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getCoursebyCode(course.getCode());
    }

    @Override
    public Course getCoursebyCode(String code) {
        List<Course>courseList = new ArrayList<>();
        Course course = null;
        try {
            RandomAccessFile input = new RandomAccessFile("course.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String Code = line.split("\\;")[0];
                String title = line.split("\\;")[1];
                double credit = Double.parseDouble(line.split("\\;")[2]);
                Course courses = new Course(Code, title,credit);
                
            if(code.equals(courses.getCode()))
            {courseList.add(courses);}
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        course = courseList.get(0);
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
       List<Course>courseList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("course.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String code = line.split("\\;")[0];
                String title = line.split("\\;")[1];
                double credit = Double.parseDouble(line.split("\\;")[2]);
                Course course = new Course(code,title,credit);
                courseList.add(course);
            }
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList; 
    }

    @Override
    public Course update(String code, Course course) {
        List<Course>courseList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("course.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String Code = line.split("\\;")[0];
                String title = line.split("\\;")[1];
                double credit = Double.parseDouble(line.split("\\;")[2]);
                Course Course = new Course(code,title,credit);
                courseList.add(Course);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("course.txt","rw");
            output.setLength(0);
            for(int i=0; i<courseList.size(); i++)
            {
                if(courseList.get(i).getCode().equals(code))            
                {String s = ""+code+";"+course.getTitle()+";"+course.getCredit()+"";
                    output.writeBytes(s+"\n");}
                else
                {String s = ""+courseList.get(i).getCode()+";"+courseList.get(i).getTitle()+";"+courseList.get(i).getCredit()+"";
                    output.writeBytes(s+"\n");}
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return getCoursebyCode(code);
    }

    @Override
    public void delete(String code) {
        List<Course>courseList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("course.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String Code = line.split("\\;")[0];
                String title = line.split("\\;")[1];
                double credit = Double.parseDouble(line.split("\\;")[2]);
                Course Course = new Course(code,title,credit);
                courseList.add(Course);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("course.txt","rw");
            output.setLength(0);
            for(int i=0; i<courseList.size(); i++)
            {
                if(!courseList.get(i).getCode().equals(code))            
                {String s = ""+courseList.get(i).getCode()+";"+courseList.get(i).getTitle()+";"+courseList.get(i).getCredit()+"";
                    output.writeBytes(s+"\n");}
                /*else
                    output.setLength(0);*/
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll() {
        try {
            RandomAccessFile output = new RandomAccessFile("course.txt","rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
