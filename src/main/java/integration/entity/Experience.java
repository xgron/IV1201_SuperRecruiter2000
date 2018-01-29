package integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="experience")
public class Experience implements Serializable{
    @Id
    @Column(name="person_ssn")
    private int personSSN;

    @Id
    @Column(name="competence_name")
    private String competenceName;

    @Column
    private float years;

    public Experience() {
    }

    public Experience(int personSSN, String competenceName) {
        this.personSSN = personSSN;
        this.competenceName = competenceName;
    }

    public Experience(int personSSN, String competenceName, float years) {
        this.personSSN = personSSN;
        this.competenceName = competenceName;
        this.years = years;
    }

    public int getPersonSSN() {
        return personSSN;
    }

    public void setPersonSSN(int personSSN) {
        this.personSSN = personSSN;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public float getYears() {
        return years;
    }

    public void setYears(float years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "personSSN=" + personSSN +
                ", competenceName='" + competenceName + '\'' +
                ", years=" + years +
                '}';
    }
}
