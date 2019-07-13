package javadaodemo;

import java.util.Objects;

/**
 *
 * @author shakur chowdhury
 */
public class Student {
    private String Id;
    private String name;
    private String mail;

    public Student(String Id, String name, String mail) {
        this.Id = Id;
        this.name = name;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Student{" + "Id=" + Id + ", name=" + name + ", mail=" + mail + '}';
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }
    
    
}
