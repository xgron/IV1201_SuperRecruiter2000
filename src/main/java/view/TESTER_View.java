package view;

import controller.HomeController;
import model.ErrorHandling;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;
import shared.PersonDTO;

import java.util.Arrays;
import java.util.List;

public class TESTER_View {
    public static void main(String[] args) {
        System.out.println("Testing...");
        HomeController hc = new HomeController();


        // PERSON 1 -----------------------------------------
        System.out.print("*** Creating userDTO in view...");
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("mail@mail.com");
        personDTO.setFirstName("Bruce");
        personDTO.setSurname("Banner");
        personDTO.setPassword("hulk");
        personDTO.setSsn("100000");
        personDTO.setUserName("thehulk");
        System.out.println("done.");

        System.out.print("*** Inserting object to DB...");
        try{
            hc.registerUser(personDTO);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterUserExeption userExeption){
            System.out.println("failed. " + userExeption.getMessage());
        }


        System.out.print("*** Creating ExperienceDTO in view...");
        String userID = hc.getUserID("thehulk");
        ExperienceDTO e1 = new ExperienceDTO("Ljustekniker", 4.51);
        ExperienceDTO e2 = new ExperienceDTO("Ljudtekniker", 5.79);
        List<ExperienceDTO> experiences = Arrays.asList(e1, e2);
        System.out.println("Object created: " + experiences);
        System.out.println("done.");

        System.out.print("*** Creating DateDTO in view...");
        DateDTO d1 = new DateDTO("2010-01-10", "2010-02-11");
        DateDTO d2 = new DateDTO("2011-01-10", "2011-02-11");
        DateDTO d3 = new DateDTO("2012-01-10", "2012-02-11");
        List<DateDTO> dates = Arrays.asList(d1, d2, d3);
        System.out.println("Object created: " + dates);
        System.out.println("done.");

        System.out.println("*** Creating ApplicationDTO in view using ExperienceDTO and DateDTO...");
        ApplicationDTO application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try{
            hc.registerApplication(application);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterApplicationExeption rue){
            System.out.println("failed. " + rue.getMessage());
        }
        // END OF PERSON 1 --------------------------------------

        // PERSON 2 -----------------------------------------
        System.out.print("*** Creating userDTO in view...");
        personDTO = new PersonDTO();
        personDTO.setEmail("mail@mail.com");
        personDTO.setFirstName("Tony");
        personDTO.setSurname("Stark");
        personDTO.setPassword("im");
        personDTO.setSsn("110000");
        personDTO.setUserName("ironman");
        System.out.println("done.");

        System.out.print("*** Inserting object to DB...");
        try{
            hc.registerUser(personDTO);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterUserExeption userExeption){
            System.out.println("failed. " + userExeption.getMessage());
        }


        System.out.print("*** Creating ExperienceDTO in view...");
        userID = hc.getUserID("ironman");
        e1 = new ExperienceDTO("Bingoutropare", 2);
        e2 = new ExperienceDTO("Poppare", 15);
        experiences = Arrays.asList(e1, e2);
        System.out.println("Object created: " + experiences);
        System.out.println("done.");

        System.out.print("*** Creating DateDTO in view...");
        d1 = new DateDTO("2009-01-10", "2011-02-11");
        d2 = new DateDTO("2012-01-10", "2013-02-11");
        d3 = new DateDTO("2015-01-10", "2016-02-11");
        dates = Arrays.asList(d1, d2, d3);
        System.out.println("Object created: " + dates);
        System.out.println("done.");

        System.out.println("*** Creating ApplicationDTO in view using ExperienceDTO and DateDTO...");
        application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try{
            hc.registerApplication(application);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterApplicationExeption rue){
            System.out.println("failed. " + rue.getMessage());
        }
        // END OF PERSON 2 --------------------------------------

        // PERSON 3 -----------------------------------------
        System.out.print("*** Creating userDTO in view...");
        personDTO = new PersonDTO();
        personDTO.setEmail("mail@mail.com");
        personDTO.setFirstName("Thor");
        personDTO.setSurname("Odinson");
        personDTO.setPassword("thor");
        personDTO.setSsn("111000");
        personDTO.setUserName("thor");
        System.out.println("done.");

        System.out.print("*** Inserting object to DB...");
        try{
            hc.registerUser(personDTO);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterUserExeption userExeption){
            System.out.println("failed. " + userExeption.getMessage());
        }


        System.out.print("*** Creating ExperienceDTO in view...");
        userID = hc.getUserID("thor");
        e1 = new ExperienceDTO("Karuselldrift", 2);
        e2 = new ExperienceDTO("Ljustekniker", 15);
        experiences = Arrays.asList(e1, e2);
        System.out.println("Object created: " + experiences);
        System.out.println("done.");

        System.out.print("*** Creating DateDTO in view...");
        d1 = new DateDTO("2005-01-10", "2010-02-11");
        d2 = new DateDTO("2013-01-10", "2015-02-11");
        d3 = new DateDTO("2020-01-10", "2022-02-11");
        dates = Arrays.asList(d1, d2, d3);
        System.out.println("Object created: " + dates);
        System.out.println("done.");

        System.out.println("*** Creating ApplicationDTO in view using ExperienceDTO and DateDTO...");
        application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try{
            hc.registerApplication(application);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterApplicationExeption rue){
            System.out.println("failed. " + rue.getMessage());
        }
        // END OF PERSON 3 --------------------------------------

        System.out.println("ALL APPLICANTS: ");
        System.out.println(hc.getApplicants());
    }
}
