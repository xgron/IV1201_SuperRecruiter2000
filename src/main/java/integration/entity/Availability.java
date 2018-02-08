package integration.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="availability")
public class Availability implements Serializable{

    @OneToOne
    @JoinColumn(name="person_ssn")
    private Person person;

    @Id
    @Column(name="start_date")
    private java.sql.Date startDate;

    @Column(name="end_date")
    private java.sql.Date endDate;

    public Availability() {
    }

    public Availability(Person person, Date startDate) {
        this.person = person;
        this.startDate = startDate;
    }

    public Availability(Person person, Date startDate, Date endDate) {
        this.person = person;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "person=" + person +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
