/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

/**
 *
 * @author shakur Chowdhury
 */
public class JavaDAODemo {

  public JavaDAODemo(){
      StudentDataAccessObject s = new StudentDAOImplForSQL();
      StudentDataAccessObject f = new StudentDAOImplForFile();
      FacultyDataAccessObject g = new FacultyDAOImplForSQL();
      Student student = new Student("112","karim","chowdhuryshakur@gmail.com");
      //s.insert(student);
      //s.getAllStudent().stream().forEach(System.out::println);
      //s.getAllStudent().stream().forEach(Student -> System.out.println(student));
      //System.out.println(s.getStduentbyID("112"));
      //s.update("112", student);
      //s.delete("112");
      
      //f.insert(student);
      //f.getAllStudent().stream().forEach(System.out::println);
      //System.out.println(f.getStduentbyID("112"));
      //f.delete("112");
      //f.update("111", student);
     
      //g.delete("KMH");
  }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JavaDAODemo();
    }
    
}
