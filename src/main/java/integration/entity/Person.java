package integration.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="person")
public class Person {

    @Id
    @Column(name = "user_id")
    private String userID;

    @NotNull
    @Size(min = 2, max = 30)
    @Column
    private String name;

    @Column
    @Size(min = 2, max = 30)
    private String surname;

    @Column
    @Range(min=100000, max=999999)
    private int ssn;

    @Column
    @Email
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    private String password;

    @Column
    @NotEmpty
    private String username;

    @Column
    private Boolean hired;

    @Column(name = "registration_date")
    private java.sql.Date registrationdate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="person")
    private List<Experience> experiences;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="person", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    @OneToOne
    @JoinColumn(name="Role_name")
    private Role role;

    public Person(String userID, String name, String surname, int ssn, String email, String password, String username, Boolean hired, Date registrationdate, List<Availability> availabilities, Role role) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.username = username;
        this.hired = hired;
        this.registrationdate = registrationdate;
        this.availabilities = availabilities;
        this.role = role;
    }

    public Person() {
    }

    public void addAvailability(Availability availability){
        if(availabilities == null){
            availabilities = new ArrayList();
        }
        availabilities.add(availability);
        availability.setPerson(this);
    }

    public void addExperience(Experience experience){
        if(experiences == null){
            experiences = new ArrayList();
        }
        experiences.add(experience);
        experience.setPerson(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ssn=" + ssn +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", hired=" + hired +
                ", registrationdate=" + registrationdate +
                ", availabilities=" + availabilities +
                ", role=" + role +
                ", experiences=" + experiences +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
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

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

