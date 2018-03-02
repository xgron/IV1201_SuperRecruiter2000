package view.entrypoints;

import controller.HomeController;
import shared.CompetencyDTO;
import shared.PublicApplicationDTO;
import view.response.CompetencyRest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfet on 2018-03-01.
 */

@Path("/competencies")
public class CompetenciesEntryPoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompetencyDTO> getCompetencies() {
        HomeController hc = new HomeController();
        List<String> competencies = hc.getPossibleCompetences();
        List<CompetencyDTO> comp = competencyResy(competencies);
        CompetencyRest cp = new CompetencyRest(comp);
        return comp;
    }

    private List<CompetencyDTO> competencyResy(List<String> comptencies) {
        List<CompetencyDTO>  restCompetencies = new ArrayList<CompetencyDTO>();
        for (String comp: comptencies) {
            CompetencyDTO temp = new CompetencyDTO(comp);
            System.out.println(temp.getName());
            restCompetencies.add(temp);
        }
        return restCompetencies;
    }

}
