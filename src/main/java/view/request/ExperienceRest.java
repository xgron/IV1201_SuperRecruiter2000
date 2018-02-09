package view.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by enfet on 2018-02-05.
 */
@XmlRootElement(name = "ExperienceRest")
public class ExperienceRest {

    @XmlElement(name = "name")
    private String name;


    @XmlElement(name = "years" )
    private String years;

    public ExperienceRest(String name, String years) {
        this.name = name;
        this.years = years;
    }

    public ExperienceRest(){
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}

