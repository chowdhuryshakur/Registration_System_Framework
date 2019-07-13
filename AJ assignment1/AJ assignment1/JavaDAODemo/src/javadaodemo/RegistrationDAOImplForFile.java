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
public class RegistrationDAOImplForFile implements RegistrationDataAccessObject {

    @Override
    public Registration insert(Registration registration) {
        try {
            RandomAccessFile output = new RandomAccessFile("registration.txt","rw");
            String s = ""+registration.getId()+";"+registration.getSectionId()+"";
            long existingLength = output.length();
          output.seek(existingLength);
            output.writeBytes(s+"\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getRegistrationbyIDAndSub(registration.getId(), registration.getSectionId());
    }

    @Override
    public Registration getRegistrationbyIDAndSub(String studentId, int secId) {
        List<Registration>registrationList = new ArrayList<>();
        Registration registration = null;
        try {
            RandomAccessFile input = new RandomAccessFile("registration.txt","r");
            String line;            
            while ((line = input.readLine()) != null) {
                String studentid = line.split("\\;")[0];
                int secID = Integer.parseInt(line.split("\\;")[1]);
                Registration Registration = new Registration(studentid, secID);
                
            if(studentId.equals(Registration.getId()) && secId == Registration.getSectionId())
            {registrationList.add(Registration);}
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
        registration = registrationList.get(0);
        return registration;
    }

    @Override
    public List<Registration> getAllRegistration() {
       List<Registration>registrationList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("registration.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                 String studentid = line.split("\\;")[0];
                int secID = Integer.parseInt(line.split("\\;")[1]);
                Registration registration = new Registration(studentid, secID);
                registrationList.add(registration);
            }
      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrationList; 
    }

    @Override
    public Registration update(String id, int secId, Registration registration) {
        List<Registration>registrationList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("registration.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String studentid = line.split("\\;")[0];
                int secID = Integer.parseInt(line.split("\\;")[1]);
                Registration registrations = new Registration(studentid, secID);
                registrationList.add(registrations);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("registration.txt","rw");
            output.setLength(0);
            for(int i=0; i<registrationList.size(); i++)
            {
                if(registrationList.get(i).getId().equals(id) && registrationList.get(i).getSectionId()==secId)            
                {String s = ""+registration.getId()+";"+registration.getSectionId()+"";
                    output.writeBytes(s+"\n");}
                else
                {String s = ""+registrationList.get(i).getId()+";"+registrationList.get(i).getSectionId()+"";
                    output.writeBytes(s+"\n");}
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return getRegistrationbyIDAndSub(registration.getId(), registration.getSectionId());
    }

    @Override
    public void delete(String id, int secId) {
        List<Registration>registrationList = new ArrayList<>();
        try {
            RandomAccessFile input = new RandomAccessFile("registration.txt","r");
            String line;
            
            while ((line = input.readLine()) != null) {
                String studentid = line.split("\\;")[0];
                int secID = Integer.parseInt(line.split("\\;")[1]);
                Registration Registration = new Registration(studentid, secID);
                registrationList.add(Registration);
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            RandomAccessFile output = new RandomAccessFile("registration.txt","rw");
            output.setLength(0);
            for(int i=0; i<registrationList.size(); i++)
            {
                if(!registrationList.get(i).getId().equals(id) && registrationList.get(i).getSectionId()!=secId)            
                {String s = ""+registrationList.get(i).getId()+";"+registrationList.get(i).getSectionId()+"";
                    output.writeBytes(s+"\n");}
                /*else
                    output.setLength(0);*/
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Override
    public void deleteAll() {
        try {
            RandomAccessFile output = new RandomAccessFile("registration.txt","rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOImplForFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
