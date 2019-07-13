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
public class StudentDAOImplForFile implements StudentDataAccessObject {

    @Override
    public Student insert(Student student) {
        try {
            RandomAccessFile output = new RandomAccessFile("student.txt","rw");
            String s = ""+student.getId()+";"+student.getName()+";"+student.getMail()+"";
            long existingLength = output.length();
          output.seek(existingLength);
            output.writeBytes(s+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getStduentbyID(student.getId());
    }

    @Override
    public Student getStduentbyID(String Id) {
        List<Student>studentList = new ArrayList<>();
        Student studenT = null;
        try {
            RandomAccessFile input = new RandomAccessFile("student.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String id = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String email = line.split("\\;")[2];
                Student student = new Student(id, name,email);
                
            if(Id.equals(student.getId()))
            {studentList.add(student);}
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        studenT = studentList.get(0);
        return studenT;
    }

    @Override
    public List<Student> getAllStudent() {
       List<Student>studentList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("student.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String id = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String email = line.split("\\;")[2];
                Student student = new Student(id, name,email);
                studentList.add(student);
            }
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList; 
    }

    @Override
    public Student update(String Id, Student student) {
        List<Student>studentList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("student.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String id = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String email = line.split("\\;")[2];
                Student stu = new Student(id, name,email);
            
                studentList.add(stu);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("student.txt","rw");
            output.setLength(0);
            for(int i=0; i<studentList.size(); i++)
            {
                if(studentList.get(i).getId().equals(Id))            
                {String s = ""+Id+";"+student.getName()+";"+student.getMail()+"";
                    output.writeBytes(s+"\n");}
                else
                {String s = ""+studentList.get(i).getId()+";"+studentList.get(i).getName()+";"+studentList.get(i).getMail()+"";
                    output.writeBytes(s+"\n");}
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return getStduentbyID(Id);
    }

    @Override
    public void delete(String Id) {
        List<Student>studentList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("student.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String id = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String email = line.split("\\;")[2];
                Student student = new Student(id, name,email);
            
                studentList.add(student);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("student.txt","rw");
            output.setLength(0);
            for(int i=0; i<studentList.size(); i++)
            {
                if(!studentList.get(i).getId().equals(Id))            
                {String s = ""+studentList.get(i).getId()+";"+studentList.get(i).getName()+";"+studentList.get(i).getMail()+"";
                    output.writeBytes(s+"\n");}
                /*else
                    output.setLength(0);*/
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteAll() {
        try {
            RandomAccessFile output = new RandomAccessFile("student.txt","rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
