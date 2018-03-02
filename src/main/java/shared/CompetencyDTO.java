package shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by enfet on 2018-03-01.
 */
public class CompetencyDTO {
    private String name;

    public CompetencyDTO(){}

    public CompetencyDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
