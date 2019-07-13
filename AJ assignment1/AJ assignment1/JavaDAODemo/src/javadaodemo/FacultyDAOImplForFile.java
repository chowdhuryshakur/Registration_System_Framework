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
public class FacultyDAOImplForFile implements FacultyDataAccessObject {

    @Override
    public Faculty insert(Faculty faculty) {
        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt","rw");
            String s = ""+faculty.getInitial()+";"+faculty.getName()+";"+faculty.getRank()+"";
            long existingLength = output.length();
          output.seek(existingLength);
            output.writeBytes(s+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getFacultybyInitial(faculty.getInitial());
    }

    @Override
    public Faculty getFacultybyInitial(String initial) {
        List<Faculty>facultyList = new ArrayList<>();
        Faculty faculty = null;
        try {
            RandomAccessFile input = new RandomAccessFile("faculty.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String Initial = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String rank = line.split("\\;")[2];
                Faculty faculties = new Faculty(Initial, name,rank);
                
            if(initial.equals(faculties.getInitial()))
            {facultyList.add(faculties);}
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        faculty = facultyList.get(0);
        return faculty;
    }

    @Override
    public List<Faculty> getAllFaculty() {
       List<Faculty>facultyList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("faculty.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String Initial = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String rank = line.split("\\;")[2];
                Faculty faculty = new Faculty(Initial, name,rank);
                facultyList.add(faculty);
            }
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facultyList; 
    }

    @Override
    public Faculty update(String initial, Faculty faculty) {
        List<Faculty>facultyList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("faculty.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String Initial = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String rank = line.split("\\;")[2];
                Faculty Faculty = new Faculty(Initial, name,rank);
                facultyList.add(faculty);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt","rw");
            output.setLength(0);
            for(int i=0; i<facultyList.size(); i++)
            {
                if(facultyList.get(i).getInitial().equals(initial))            
                {String s = ""+initial+";"+faculty.getName()+";"+faculty.getRank()+"";
                    output.writeBytes(s+"\n");}
                else
                {String s = ""+facultyList.get(i).getInitial()+";"+facultyList.get(i).getName()+";"+facultyList.get(i).getRank()+"";
                    output.writeBytes(s+"\n");}
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return getFacultybyInitial(initial);
    }

    @Override
    public void delete(String initial) {
        List<Faculty>facultyList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("faculty.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String Initial = line.split("\\;")[0];
                String name = line.split("\\;")[1];
                String rank = line.split("\\;")[2];
                Faculty faculty = new Faculty(Initial, name,rank);
                facultyList.add(faculty);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt","rw");
            output.setLength(0);
            for(int i=0; i<facultyList.size(); i++)
            {
                if(!facultyList.get(i).getInitial().equals(initial))            
                {String s = ""+facultyList.get(i).getInitial()+";"+facultyList.get(i).getName()+";"+facultyList.get(i).getRank()+"";
                    output.writeBytes(s+"\n");}
                /*else
                    output.setLength(0);*/
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteAll() {
        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt","rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
