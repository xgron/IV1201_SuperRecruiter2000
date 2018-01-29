package integration;

import integration.entity.*;
import integration.operation.PersonOperation;
import integration.operation.AvailabilityOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Calendar;



public class integration {
    private static SessionFactory factory = new Configuration().configure()
                                                .addAnnotatedClass(Role.class)
                                                .addAnnotatedClass(Person.class)
                                                .addAnnotatedClass(Experience.class)
                                                .addAnnotatedClass(Competence.class)
                                                .addAnnotatedClass(Availability.class)
                                                .buildSessionFactory();

    private static Session session = factory.getCurrentSession();

    public static void main(String[] args) {
        java.sql.Date from = new java.sql.Date(120, 6, 01);
        System.out.println(AvailabilityOperation.readAvailability(12345, from, factory, session));


    }


    //TESTERS
    public static void makeTestPerson(){
        int ssn = 54321;
        String name = "Frodo";
        String surname = "Baggins";
        String email = "bag@end.com";
        String password = "theonering";
        String role_name = "applicant";
        String username = "frodo";
        Boolean hired = null;
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate);

        PersonOperation.createPerson(person, factory, session);
    }

    public static void makeTestAvailability(){
        java.sql.Date from = new java.sql.Date(120, 6, 01);
        java.sql.Date to = new java.sql.Date(120, 6, 30);

        Availability av = new Availability(12345, from, to);
        System.out.println(av);
        AvailabilityOperation.createAvailability(av, factory, session);
    }
}
