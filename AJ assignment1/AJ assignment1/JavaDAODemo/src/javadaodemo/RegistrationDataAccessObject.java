/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.util.List;

/**
 *
 * @author User
 */
public interface RegistrationDataAccessObject {
    Registration insert(Registration registration);
    Registration getRegistrationbyIDAndSub(String studentId, int secId);
    List <Registration> getAllRegistration();
    Registration update(String id, int secId, Registration registration);
    void delete(String id, int secId);
    void deleteAll();
}
