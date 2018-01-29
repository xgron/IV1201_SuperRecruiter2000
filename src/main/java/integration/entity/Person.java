package integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column
    private int ssn;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String role_name;

    @Column
    private String username;

    @Column
    private Boolean hired;

    @Column(name="registration_date")
    private java.sql.Date registrationdate;

    public Person() {
    }

    public Person(int ssn, String name, String surname, String email, String password, String role_name, String username, Boolean hired, Date registrationdate) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role_name = role_name;
        this.username = username;
        this.hired = hired;
        this.registrationdate = registrationdate;
    }

    public Person(int ssn, String name, String surname, String email, String password, String role_name, String username, Date registrationdate) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role_name = role_name;
        this.username = username;
        this.registrationdate = registrationdate;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getHired() {
        return hired;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn=" + ssn +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role_name='" + role_name + '\'' +
                ", username='" + username + '\'' +
                ", hired=" + hired +
                ", registrationdate=" + registrationdate +
                '}';
    }
}
