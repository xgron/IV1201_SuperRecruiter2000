package model;

import integration.DBPortal;
import integration.entity.Availability;
import integration.entity.Competence;
import integration.entity.Experience;
import integration.entity.Person;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;

import java.util.List;

public class Application {
    DBPortal portal;

    /**
     *  Constructor for the Application class.
     *
     * @param   portal  a DBPortal object(Database Portal). The model communicates only with DBPortal in the Integration layer.
     */
    public Application(DBPortal portal){this.portal = portal;}

    /**
     *  This method is called from the Control layer when a someone is trying to register a new application.
     *
     * It returns a boolean, an Exception if there was an error with the application. True if it was successfully sent to the integration layer.
     *
     * It returns an Exception if the userID does not exist, using userIDExists(String UserID).
     * It returns an Exception if An experience has a competence that does not exist, using competenceExist(String competence)
     * It returns an Exception if two availabilities contain the same starting date.
     *
     * if none of the above occur, it sends the Experience list and the Availability list (along with the userID) to
     * the integration layer, and then returns true.
     *
     * @param   application  An ApplicationDTO(Person Data Transfer Object), which contains all necessary data for an application.
     * @return  boolean  Exception if error, true if successful registration of application
     */
    public boolean registerApplication(ApplicationDTO application) throws ErrorHandling.RegisterApplicationExeption{
        if(!userIDExist(application.getUserID()))
            throw new ErrorHandling.RegisterApplicationExeption("Invalid UserID!");
        for(ExperienceDTO to : application.getExperience()){
            if (!competenceExist(to.getName()))
                    throw new ErrorHandling.RegisterApplicationExeption("This competence does not exist!");
        }
        int i = 1;
        for (DateDTO to : application.getAvailabilities()){
            for (DateDTO to1 : application.getAvailabilities().subList(i,application.getAvailabilities().size())){
                if(to1.getStart().equals(to.getStart()))
                    throw new ErrorHandling.RegisterApplicationExeption("Invalid Availabilities!");
            }
            i++;
        }
        competenceListToDB(application.getUserID(),application.getExperience());
        avalabilityListToDB(application.getUserID(), application.getAvailabilities());
        return true;
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
            portal.createExperience(experience);
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
            portal.createAvailability(availability);
        }
    }

    /**
     * This method checks if the competence exist in the DB
     * @param  competence     the competence that is checked
     * @return      the answer to "does competence XXX exist in the DB?"
     */
    public Boolean competenceExist(String competence){
        try{
            List<Competence> competenceList = portal.getCompetence(competence);
            return !competenceList.isEmpty();
        }catch (Exception e){

        }
        return null;
    }

    /**
     * This method searches the DB for the ssn matching the specified userID
     * @param userID    the user ID
     * @return      returns the found ssn
     */
    public int searchForUserSSN(String userID){
        Person p = portal.readPerson(userID);
        return p.getSsn();
    }

    private static java.sql.Date stringToSQLDate(String dateString){
        String[] tempStrArr = dateString.split("-");
        int year = Integer.parseInt(tempStrArr[0]) - 1900;
        int month = Integer.parseInt(tempStrArr[1]) - 1;
        int day = Integer.parseInt(tempStrArr[2]);
        java.sql.Date date = new java.sql.Date(year, month, day);
        return date;
    }

    private static double roundToOneDecimal(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * This method checks if the userID exist
     * @param  userID     the username that is checked
     * @return      whether the userID exists or not.
     */
    public Boolean userIDExist(String userID){
        try{
            List<Person> personList = portal.getPersonWithUserID(userID);
            return !personList.isEmpty();

        } catch(Exception e){

        }
        return null;

    }
}
