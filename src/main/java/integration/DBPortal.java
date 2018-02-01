package integration;


import integration.entity.*;
import integration.entity.Person;
import integration.operation.PersonOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import shared.PersonDTO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


public class DBPortal {
    private static SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Experience.class)
            .addAnnotatedClass(Competence.class)
            .addAnnotatedClass(Availability.class)
            .buildSessionFactory();

    private static Session session = factory.getCurrentSession();

    public void registerUser(PersonDTO personDTO){
        //ALSO INCLUDE KEY AFTER DB IS CHANGED
        int ssn = personDTO.getSsn();
        String name = personDTO.getFirstName();
        String surname = personDTO.getSurname();
        String email = personDTO.getEmail();
        String password = personDTO.getPassword();
        String role_name = "applicant";
        String username = personDTO.getUserName();
        Boolean hired = null;
        Date registrationdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String userID = personDTO.getUserId();

        Person person = new Person(ssn, name, surname, email, password, role_name, username, hired, registrationdate, userID);

        PersonOperation.createPerson(person, factory, session);
    }

    public Boolean usernameTaken(String username){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Person> personList = session.createQuery("from Person p where p.username='" + username + "'").getResultList();
            int c = 0;
            for(Person tp : personList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0)  return false;
            else return true;
        }finally {
            System.out.println("usernameTaken DONE.");
        }

    }

    public Boolean ssnTaken(int ssn){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Person> personList = session.createQuery("from Person p where p.ssn='" + ssn + "'").getResultList();
            int c = 0;
            for(Person tp : personList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0)  return false;
            else return true;
        }finally {
            System.out.println("ssnTaken DONE");
        }

    }
}