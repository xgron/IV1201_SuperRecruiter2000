package integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competence")
public class Competence {
    @Id
    @Column
    private String name;

    public Competence() {
    }

    public Competence(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "name='" + name + '\'' +
                '}';
    }
}
