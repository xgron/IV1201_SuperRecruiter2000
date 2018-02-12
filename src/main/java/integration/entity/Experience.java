package integration.entity;

import javax.persistence.*;

@Entity
@Table(name="experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private double years;

    @OneToOne
    @JoinColumn(name="Competence_Name")
    private Competence competence;

    @ManyToOne
    @JoinColumn(name="Person_user_id")
    private Person person;


    public Experience() {
    }

    public Experience(double years, Competence competence) {
        this.years = years;
        this.competence = competence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getYears() {
        return years;
    }

    public void setYears(double years) {
        this.years = years;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", years=" + years +
                ", competence=" + competence +
                '}';
    }
}
