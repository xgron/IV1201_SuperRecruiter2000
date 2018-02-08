package integration;

import integration.entity.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        DBPortal portal = new DBPortal();

        //CREATE OBJECTS
        /*
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Role role = new Role("applicant");
        Person person = new Person(
                "TESTUSERIDTWO",
                "Bob",
                "Dylan",
                8888888,
                "dylan@email.com",
                "password",
                "dylan",
                null,
                registrationdate,
                null,
                role
                );
         */

        Person person = portal.readPerson("TESTUSERIDONE");
        Competence bingoutropare = new Competence("Bingoutropare");
        Competence karuselldrift = new Competence("Karuselldrift");
        Experience bingoExperience = new Experience(5, bingoutropare, person);
        Experience karusellExperience = new Experience(3, karuselldrift, person);




        System.out.println(person);


        //ENTER OBJECTS TO DB
        //portal.createPerson(person);

    }
}
