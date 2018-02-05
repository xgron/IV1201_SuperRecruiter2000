package shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExperienceDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "years" )
    private float years;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExperienceDTO{" +
                "name='" + name + '\'' +
                ", years=" + years +
                '}';
    }

    public float getYears() {
        return years;
    }

    public void setYears(float years) {
        this.years = years;
    }
}
