package integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competence")
public class Competence {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="name_sv")
    private String nameSv;

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

    public String getNameSv() {
        return nameSv;
    }

    public void setNameSv(String nameSv) {
        this.nameSv = nameSv;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "name='" + name + '\'' +
                ", nameSv='" + nameSv + '\'' +
                '}';
    }
}
