package integration;


import integration.entity.*;
import integration.entity.Person;
import integration.operation.AvailabilityOperation;
import integration.operation.ExperienceOperation;
import integration.operation.PersonOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shared.DateDTO;
import shared.ExperienceDTO;
import shared.PersonDTO;

import java.sql.Date;

import java.util.Calendar;
import java.util.List;


public class DBPortal {
    private Factory factoryObj = new Factory();
    private SessionFactory factory = factoryObj.getFactory();

    private Session session = factory.getCurrentSession();

    /**
     * This method will register a user to the database
     * @param  personDTO    the object containing all user data to be saved to the database
     */
    public void registerUser(PersonDTO personDTO){
        int ssn = Integer.parseInt(personDTO.getSsn());
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

        PersonOperation.createPerson(person, factory);
    }

    /**
     * This method checks if the username is taken or not. Will return true if the username is taken, and false if it is free.
     * @param  username     the username that is checked
     * @return      the answer to "is username XXX taken?"
     */
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
            if(c == 0){
                System.out.println("* username: " + username + " is not taken.");
                return false;
            }
            else{
                System.out.println("* username: " + username + " is taken.");
                return true;
            }
        }finally {
        }

    }

    /**
     * This method checks if the userID exist
     * @param  userID     the username that is checked
     * @return      whether the userID exists or not.
     */
    public Boolean userIDExist(String userID){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Person> personList = session.createQuery("from Person p where p.userID='" + userID + "'").getResultList();
            int c = 0;
            for(Person tp : personList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0){
                System.out.println("* userID: " + userID + " does not exist.");
                return false;
            }
            else{
                System.out.println("* userID: " + userID + " does exist.");
                return true;
            }
        }finally {
        }

    }

    /**
     * This method checks if the SSN is taken or not. Will return true if the SSN is taken, and false if it is free.
     * @param  ssn     the SSN that is checked
     * @return      the answer to "is SSN XXX taken?"
     */
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
            if(c == 0){
                System.out.println("* SSN: " + ssn + " is not taken.");
                return false;
            }
            else{
                System.out.println("* SSN: " + ssn + " is taken.");
                return true;
            }
        }finally {
        }
    }

    /**
     * This method checks if the competence exist in the DB
     * @param  competence     the competence that is checked
     * @return      the answer to "does competence XXX exist in the DB?"
     */
    public Boolean competenceExist(String competence){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Competence> competenceList = session.createQuery("from Competence c where c.name='" + competence + "'").getResultList();
            int c = 0;
            for(Competence tc : competenceList){
                c++;
            }
            session.getTransaction().commit();
            if(c == 0){
                System.out.println("* Competence: " + competence + " does not exist in DB.");
                return false;
            }
            else{
                System.out.println("* Competence: " + competence + " does exist in DB.");
                return true;
            }
        }finally {
        }
    }

    /**
     * This method enters the entire DateDTO-list into the availability table in the DB
     * @param  userID     the current user
     * @param  availabilities  a list with DateDTO objects
     */
    public void avalabilityListToDB(String userID, List<DateDTO> availabilities){
        int ssn = searchForUserSSN(userID);
        for(DateDTO d : availabilities){
            Availability availability = new Availability(ssn,stringToSQLDate(d.getStart()), stringToSQLDate(d.getEnd()));
            AvailabilityOperation.createAvailability(availability, factory);
        }
    }

    private static java.sql.Date stringToSQLDate(String dateString){
        String[] tempStrArr = dateString.split("-");
        int year = Integer.parseInt(tempStrArr[0]) - 1900;
        int month = Integer.parseInt(tempStrArr[1]) - 1;
        int day = Integer.parseInt(tempStrArr[2]);
        java.sql.Date date = new java.sql.Date(year, month, day);
        return date;
    }

    /**
     * This method enters the entire ExperienceDTO-list into the availability table in the DB
     * @param  userID     the current user
     * @param  experiences  a list with Experience DTO objects
     */
    public void competenceListToDB(String userID, List<ExperienceDTO> experiences){
        int ssn = searchForUserSSN(userID);
        for(ExperienceDTO eDTO : experiences){
            Experience experience = new Experience(ssn, eDTO.getName(), roundToOneDecimal(eDTO.getYears(), 1));
            ExperienceOperation.createExperience(experience, factory);
        }

    }

    private static double roundToOneDecimal(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * This method searches the DB for the ssn matching the specified userID
     * @param userID    the user ID
     * @return      returns the found ssn
     */
    public int searchForUserSSN(String userID){
        Person p = PersonOperation.readPerson(userID, factory);
        return p.getSsn();
    }
}