/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadaodemo;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Registration {
    private String id;
    private int sectionId;

    public Registration(String id, int sectionId) {
        this.id = id;
        this.sectionId = sectionId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Registration{" + "id=" + id + ", sectionId=" + sectionId + '}';
    }

  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registration other = (Registration) obj;
        if (this.sectionId != other.sectionId) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
