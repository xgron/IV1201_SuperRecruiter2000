package model;

import integration.entity.Person;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;

import java.util.Arrays;
import java.util.List;

public class TESTER_Model {
    public static void main(String[] args) {
        Model model = new Model();

        String userID = "tlilmejkafqmiwqejoprpbtqt";

        ExperienceDTO e1 = new ExperienceDTO("Karuselldrift", 4.51);
        ExperienceDTO e2 = new ExperienceDTO("Djurskötare", 5.79);
        List<ExperienceDTO> experiences = Arrays.asList(e1, e2);

        DateDTO d1 = new DateDTO("2016-01-10", "2013-02-11");
        DateDTO d2 = new DateDTO("2017-01-10", "2014-02-11");
        DateDTO d3 = new DateDTO("2018-01-10", "2015-02-11");
        List<DateDTO> dates = Arrays.asList(d1, d2, d3);

        ApplicationDTO application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try {
            model.application.registerApplication(application);
        } catch (ErrorHandling.RegisterApplicationException rue) {
            //TO DO
            System.out.print("Application failed to register. Cause: ");
            System.out.println(rue.getMessage());

        }
    }
}
