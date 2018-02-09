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
        String userID = "ABCDEFGHIJKLM";
        ExperienceDTO e1 = new ExperienceDTO("Ljustekniker", 4.51);
        ExperienceDTO e2 = new ExperienceDTO("Ljudtekniker", 5.79);
        List<ExperienceDTO> experiences = Arrays.asList(e1, e2);
        System.out.println("done.");

        System.out.print("*** Creating DateDTO in view...");
        DateDTO d1 = new DateDTO("2010-01-10", "2010-02-11");
        DateDTO d2 = new DateDTO("2011-01-10", "2011-02-11");
        DateDTO d3 = new DateDTO("2012-01-10", "2012-02-11");
        List<DateDTO> dates = Arrays.asList(d1, d2, d3);
        System.out.println("done.");

        System.out.print("*** Creating ApplicationDTO in view using ExperienceDTO and DateDTO...");
        ApplicationDTO application = new ApplicationDTO(dates, experiences, userID);
        System.out.println("Object created: " + application);
        try{
            hc.registerApplication(application);
            System.out.println("done.");
        }catch(ErrorHandling.RegisterApplicationExeption rue){
            System.out.println("failed. " + rue.getMessage());
        }


    }
}
