package integration.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private int ssn;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private Boolean hired;

    @Column(name = "registration_date")
    private java.sql.Date registrationdate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="person")
    private List<Experience> experiences;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="person")
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
}

