package view;

import controller.HomeController;
import model.ErrorHandling;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;

import java.util.Arrays;
import java.util.List;

public class viewTest {
    public static void main(String[] args) {

        System.out.println("Testing...");

        HomeController hc = new HomeController();

        String userID = "nxdvzpiugcoqkubaqbdluulqp";

        ExperienceDTO e1 = new ExperienceDTO("Ljustekniker", 4.51);
        ExperienceDTO e2 = new ExperienceDTO("Ljudtekniker", 5.79);
        List<ExperienceDTO> experiences = Arrays.asList(e1, e2);

        DateDTO d1 = new DateDTO("2010-01-10", "2010-02-11");
        DateDTO d2 = new DateDTO("2011-01-10", "2011-02-11");
        DateDTO d3 = new DateDTO("2012-01-10", "2012-02-11");
        List<DateDTO> dates = Arrays.asList(d1, d2, d3);

        ApplicationDTO application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try{
            hc.registerApplication(application);
        }catch(ErrorHandling.RegisterApplicationExeption rue){
            //TO DO
            System.out.println("Application failed to register.");
        }

    }
}
