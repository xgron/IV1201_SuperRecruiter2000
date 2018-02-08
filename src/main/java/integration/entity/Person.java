package integration.entity;

import javax.persistence.*;
import java.sql.Date;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Availability_id")
    private Availability availability;

    @OneToMany
    @JoinColumn(name="Role_name")
    private Role role;

    public Person(String userID, String name, String surname, int ssn, String email, String password, String username, Boolean hired, Date registrationdate, Availability availability, Role role) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.username = username;
        this.hired = hired;
        this.registrationdate = registrationdate;
        this.availability = availability;
        this.role = role;
    }

    public Person() {
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
                ", availability=" + availability +
                ", role=" + role +
                '}';
    }
}

