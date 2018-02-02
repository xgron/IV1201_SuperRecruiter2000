package integration;

import integration.entity.*;
import integration.operation.PersonOperation;
import integration.operation.AvailabilityOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import shared.PersonDTO;

import java.sql.Date;
import java.util.Calendar;



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
    }
}
