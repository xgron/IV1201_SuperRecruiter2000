package shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExperienceDTO {

    @XmlElement(name = "name")
    private String name;


    @XmlElement(name = "years" )
    private double years;

    public ExperienceDTO(String name, double years) {
        this.name = name;
        this.years = years;
    }

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

    public double getYears() {
        return years;
    }

    public void setYears(double years) {
        this.years = years;
    }
}
