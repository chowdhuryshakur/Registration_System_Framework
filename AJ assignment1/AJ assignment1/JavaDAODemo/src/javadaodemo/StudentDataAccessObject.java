/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.util.List;

/**
 *
 * @author shakur Chowdhury
 */
public interface StudentDataAccessObject {
    Student insert(Student student);
    Student getStduentbyID(String Id);
    List <Student> getAllStudent();
    Student update(String Id, Student student);
    void delete(String Id);
    void deleteAll();
}
