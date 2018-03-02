package view.response;

import shared.CompetencyDTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by enfet on 2018-03-01.
 */
@XmlRootElement
public class CompetencyRest {
    @XmlElement(name = "competencies")
    private List<CompetencyDTO> competencies;

    public CompetencyRest() {}

    public CompetencyRest(List<CompetencyDTO> competencies){
        this.competencies = competencies;
    }

    public List<CompetencyDTO> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(List<CompetencyDTO> competencies) {
        this.competencies = competencies;
    }
}
