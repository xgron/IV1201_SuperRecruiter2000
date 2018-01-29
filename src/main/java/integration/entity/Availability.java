package integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="availability")
public class Availability implements Serializable{
    @Id
    @Column(name="person_ssn")
    private int personSSN;

    @Id
    @Column(name="start_date")
    private java.sql.Date startDate;

    @Column(name="end_date")
    private java.sql.Date endDate;

    public Availability() {
    }

    public Availability(int personSSN, Date startDate) {
        this.personSSN = personSSN;
        this.startDate = startDate;
    }

    public Availability(int personSSN, Date startDate, Date endDate) {
        this.personSSN = personSSN;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPersonSSN() {
        return personSSN;
    }

    public void setPersonSSN(int personSSN) {
        this.personSSN = personSSN;
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
                "personSSN=" + personSSN +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
