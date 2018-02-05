package integration;

import integration.entity.*;
import integration.operation.PersonOperation;
import integration.operation.AvailabilityOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import shared.DateDTO;
import shared.ExperienceDTO;
import shared.PersonDTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class TESTER_Integration {
  public static void main(String[] args) {
      DBPortal port = new DBPortal();

      /*
      PersonDTO person = new PersonDTO();
      person.setFirstName("Bruce");
      person.setSurname("Wayne");
      person.setSsn(1347);
      person.setEmail("bat@bat.com");
      person.setPassword("batman");
      person.setUserName("batman");
      person.setUserId("1234567890qwertyuiopasdf");
      port.registerUser(person);
      */

      //System.out.println(port.ssnTaken(1347));
      //System.out.println(port.usernameTaken("batman"));

      //System.out.println(port.competenceExist("Bingoutropa"));

      /*
      String userID = "nxdvzpiugcoqkubaqbdluulqp";

      ExperienceDTO e1 = new ExperienceDTO("Poppare", 4.51);
      ExperienceDTO e2 = new ExperienceDTO("Spöke", 5.79);
      List<ExperienceDTO> list = Arrays.asList(e1, e2);
      port.competenceListToDB(userID, list);
      */

      /*
      try{

          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          Date d1 = new java.sql.Date(sdf.parse("21/12/2012").getTime());
          Date d2 = new java.sql.Date(sdf.parse("21/12/2013").getTime());
          Date d3 = new java.sql.Date(sdf.parse("21/12/2014").getTime());
          Date d4 = new java.sql.Date(sdf.parse("21/12/2015").getTime());
          Date d5 = new java.sql.Date(sdf.parse("21/12/2016").getTime());
          Date d6 = new java.sql.Date(sdf.parse("21/12/2017").getTime());
          DateDTO dt1 = new DateDTO(d1,d2);
          DateDTO dt2 = new DateDTO(d3,d4);
          DateDTO dt3 = new DateDTO(d5,d6);
          List<DateDTO> d = Arrays.asList(dt1, dt2, dt3);
          port.avalabilityListToDB(userID, d);

      }catch (Exception e){
          e.printStackTrace();
      }
      */

    }
}
