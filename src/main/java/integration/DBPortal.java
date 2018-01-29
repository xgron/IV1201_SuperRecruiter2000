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



public class DBPortal {
    private static SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Experience.class)
            .addAnnotatedClass(Competence.class)
            .addAnnotatedClass(Availability.class)
            .buildSessionFactory();

    private static Session session = factory.getCurrentSession();

    public static void registerUser(PersonDTO persFromView){
        //ALSO INCLUDE KEY AFTER DB IS CHANGED
        int ssn = persFromView.getSsn();
        String name = persFromView.getFirstName();
        String surname = persFromView.getSurname();
        String email = persFromView.getEmail();
        String password = persFromView.getPassword();
        String role_name = "applicant";
        String username = persFromView.getUserName();
        Boolean hired = null;
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String userID = persFromView.getUserId();

        Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate, userID);

        PersonOperation.createPerson(person, factory, session);

    }
}