package integration.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.sql.Date;

@Entity
@Table(name="availability")
public class Availability{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name="start_date")
    private java.sql.Date startDate;

    @Column(name="end_date")
    private java.sql.Date endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Person_user_id")
    private Person person;

    public Availability() {
    }

    public Availability(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}