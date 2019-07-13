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
public class SectionDAOImplForFile implements SectionDataAccessObject {

    @Override
    public Section insert(Section section) {
        try {
            RandomAccessFile output = new RandomAccessFile("section.txt","rw");
            String s = ""+section.getSectionId()+";"+section.getSectionNumber()+";"+section.getSemester()+";"+section.getSeatLimit()+";"+section.getCode()+";"+section.getInitial()+"";
            long existingLength = output.length();
          output.seek(existingLength);
            output.writeBytes(s+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getSectionbyID(section.getSectionId());
    }

    @Override
    public Section getSectionbyID(int id) {
        List<Section>sectionList = new ArrayList<>();
        Section section = null;
        try {
            RandomAccessFile input = new RandomAccessFile("section.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                int secId = Integer.parseInt(line.split("\\;")[0]);
                int secNum = Integer.parseInt(line.split("\\;")[1]);
                int semester = Integer.parseInt(line.split("\\;")[2]);
                int seatLimit = Integer.parseInt(line.split("\\;")[3]);
                String courseCode = line.split("\\;")[4];
                String teacherInitial = line.split("\\;")[5];
                Section Section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                
            if(id==Section.getSectionId())
            {sectionList.add(Section);}
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        section = sectionList.get(0);
        return section;
    }

    @Override
    public List<Section> getAllSection() {
       List<Section>sectionList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("section.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                int secId = Integer.parseInt(line.split("\\;")[0]);
                int secNum = Integer.parseInt(line.split("\\;")[1]);
                int semester = Integer.parseInt(line.split("\\;")[2]);
                int seatLimit = Integer.parseInt(line.split("\\;")[3]);
                String courseCode = line.split("\\;")[4];
                String teacherInitial = line.split("\\;")[5];
                Section section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                sectionList.add(section);
            }
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sectionList; 
    }

    @Override
    public Section update(int id, Section section) {
        List<Section>sectionList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("section.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                int secId = Integer.parseInt(line.split("\\;")[0]);
                int secNum = Integer.parseInt(line.split("\\;")[1]);
                int semester = Integer.parseInt(line.split("\\;")[2]);
                int seatLimit = Integer.parseInt(line.split("\\;")[3]);
                String courseCode = line.split("\\;")[4];
                String teacherInitial = line.split("\\;")[5];
                Section Section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                sectionList.add(Section);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("section.txt","rw");
            output.setLength(0);
            for(int i=0; i<sectionList.size(); i++)
            {
                if(sectionList.get(i).getSectionId()==id)            
                {String s = ""+id+";"+sectionList.get(i).getSectionNumber()+";"+sectionList.get(i).getSemester()+";"+sectionList.get(i).getSeatLimit()+";"+sectionList.get(i).getCode()+";"+sectionList.get(i).getInitial()+"";
                    output.writeBytes(s+"\n");}
                else
                {String s = ""+sectionList.get(i).getSectionId()+";"+sectionList.get(i).getSectionNumber()+";"+sectionList.get(i).getSemester()+";"+sectionList.get(i).getSeatLimit()+";"+sectionList.get(i).getCode()+";"+sectionList.get(i).getInitial()+"";
                    output.writeBytes(s+"\n");}
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return getSectionbyID(id);
    }

    @Override
    public void delete(int id) {
        List<Section>sectionList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("section.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                int secId = Integer.parseInt(line.split("\\;")[0]);
                int secNum = Integer.parseInt(line.split("\\;")[1]);
                int semester = Integer.parseInt(line.split("\\;")[2]);
                int seatLimit = Integer.parseInt(line.split("\\;")[3]);
                String courseCode = line.split("\\;")[4];
                String teacherInitial = line.split("\\;")[5];
                Section Section = new Section(secId, secNum, semester, seatLimit, courseCode, teacherInitial);
                sectionList.add(Section);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("section.txt","rw");
            output.setLength(0);
            for(int i=0; i<sectionList.size(); i++)
            {
                if(sectionList.get(i).getSectionId()!=id)            
                {String s = ""+id+";"+sectionList.get(i).getSectionNumber()+";"+sectionList.get(i).getSemester()+";"+sectionList.get(i).getSeatLimit()+";"+sectionList.get(i).getCode()+";"+sectionList.get(i).getInitial()+"";
                    output.writeBytes(s+"\n");}
                /*else
                    output.setLength(0);*/
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void deleteAll() {
        try {
            RandomAccessFile output = new RandomAccessFile("section.txt","rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
