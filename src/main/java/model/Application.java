package model;

import integration.DBPortal;
import integration.entity.Availability;
import integration.entity.Competence;
import integration.entity.Experience;
import integration.entity.Person;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;
import shared.PublicApplicationDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.*;

public class Application {
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private boolean swedish = false;

    DBPortal portal;
    /**
     *  Constructor for the Application class.
     *
     * @param   portal  a DBPortal object(Database Portal). The model communicates only with DBPortal in the Integration layer.
     */
    public Application(DBPortal portal){
        this.portal = portal;
        }

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
    public boolean registerApplication(ApplicationDTO application) throws ErrorHandling.RegisterApplicationException {
        try{
            if(!userIDExist(application.getUserID()))
                throw new ErrorHandling.RegisterApplicationException("Invalid UserID!");
            for(ExperienceDTO to : application.getExperience()){
                if (!competenceExist(to.getName()))
                        throw new ErrorHandling.RegisterApplicationException("This competence does not exist!");
            }
            int i = 1;
            for (DateDTO to : application.getAvailabilities()){
                for (DateDTO to1 : application.getAvailabilities().subList(i,application.getAvailabilities().size())){
                    if(to1.getStart().equals(to.getStart()))
                        throw new ErrorHandling.RegisterApplicationException("Invalid Availabilities!");
                }
                i++;
            }
            competenceListToDB(application.getUserID(),application.getExperience());
            availabilityListToDB(application.getUserID(), application.getAvailabilities());
            LOG.log(Level.INFO, "The application for user " + portal.getPersonWithUserID(application.getUserID()).get(0).getName() + " " + portal.getPersonWithUserID(application.getUserID()).get(0).getSurname() + " was updated.");
            return true;

        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return false;
    }

    /**
     * This method will set the status of a specific application to true or false.
     * @param applicantID the userID of the applicant
     * @param evaluation true or false, if the user is accepted or not
     * @param recruiterID the userID of the recruiter
     * @return returns true if the evaluation was successful
     * @throws ErrorHandling.EvaluateApplicationException
     */
    public boolean evaluateApplication(String applicantID, boolean evaluation, String recruiterID) throws ErrorHandling.EvaluateApplicationException {
        try{
            List<Person> person = portal.getPersonWithUserID(applicantID);
            if(applicantID.length() != 25 || recruiterID.length() != 25 || person.isEmpty() || portal.getPersonWithUserID(recruiterID).isEmpty())
                throw new ErrorHandling.EvaluateApplicationException("Invalid UserID!");
            else if(portal.getPersonWithUserID(recruiterID).get(0).getRole().getName()!="recruiter")
                throw new ErrorHandling.EvaluateApplicationException("Unauthorized request!");
            else if(TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.getResource(person.get(0)).equals(person.get(0)))
                throw new ErrorHandling.EvaluateApplicationException("This application is currently being evaluated by someone else!");
            else{
                Person applicant = person.get(0);

                TransactionSynchronizationManager.initSynchronization();
                TransactionSynchronizationManager.bindResource(applicant, applicant);
                TransactionSynchronizationManager.setCurrentTransactionName(applicant.getUserID());
                TransactionSynchronizationManager.setActualTransactionActive(true);

                applicant.setHired(evaluation);
                portal.updatePerson(applicant);
                LOG.info("The application for user " + applicant.getName() + " " + applicant.getSurname() + " was evaluated by: " + portal.getPersonWithUserID(recruiterID).get(0).getName() + " " + portal.getPersonWithUserID(recruiterID).get(0).getSurname());
                TransactionSynchronizationManager.clear();
                return true;
                }
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return false;
    }
    /**
     * This method enters the entire ExperienceDTO-list into the availability table in the DB
     * @param  userID     the current user
     * @param  experiences  a list with Experience DTO objects
     */
    public void competenceListToDB(String userID, List<ExperienceDTO> experiences){
        try{
        Person person = portal.getPersonWithUserID(userID).get(0);
        for(ExperienceDTO eDTO : experiences){
            Competence competence = new Competence(eDTO.getName());
            Experience experience = new Experience(roundToOneDecimal(eDTO.getYears(), 1),
                                                    competence);
            person.addExperience(experience);
            portal.saveExperience(experience);
        }
        portal.updatePerson(person);
        LOG.log(Level.INFO, "The list of competences for user " + person.getName() + " " + person.getSurname() + " was updated.");
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
    }

    /**
     * This method enters the entire DateDTO-list into the availability table in the DB
     * @param  userID     the current user
     * @param  availabilities  a list with DateDTO objects
     */
    public void availabilityListToDB(String userID, List<DateDTO> availabilities){
        try{
        Person person = portal.getPersonWithUserID(userID).get(0);
        for(DateDTO d : availabilities){
            Availability availability = new Availability(stringToSQLDate(d.getStart()),
                                                         stringToSQLDate(d.getEnd()));
           person.addAvailability(availability);
           portal.createAvailability(availability);
        }
        portal.updatePerson(person);

        LOG.log(Level.INFO, "The list of availabilities for user " + person.getName() + " " + person.getSurname() + " was updated.");
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
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
        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;
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

        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;

    }

    //JAVADOC TO DO
    public List<PublicApplicationDTO> getApplicants(){
        try{
        List<PublicApplicationDTO> applicationList = new ArrayList<PublicApplicationDTO>();
        List<Person> personList = portal.getPersonWithRole("applicant");
        for(Person p : personList){
            String hired;
            if(p.getHired() == null){
                if(swedish){
                    hired = "Under övervägande";
                }else{
                    hired = "Under consideration";
                }
            }else if(p.getHired() == false){
                if(swedish){
                    hired = "Avböjd";
                }else{
                    hired = "Declined";
                }
            }else{
                if(swedish){
                    hired = "Accepterad";
                }else{
                    hired = "Accepted";
                }
            }

            List<ExperienceDTO> experienceDTOList = new ArrayList<ExperienceDTO>();
            for(Experience experience : p.getExperiences()){
                String competenceName;
                if(swedish){
                    competenceName = experience.getCompetence().getNameSv();
                }else {
                    competenceName = experience.getCompetence().getName();
                }
                ExperienceDTO experienceDTO = new ExperienceDTO(competenceName,
                        experience.getYears());
                experienceDTOList.add(experienceDTO);
            }

            List<DateDTO> availabilities = new ArrayList<DateDTO>();
            for(Availability availability : p.getAvailabilities()){
                DateDTO dateDTO = new DateDTO(availability.getStartDate().toString(),
                        availability.getEndDate().toString());
                availabilities.add(dateDTO);
            }

            PublicApplicationDTO tempPA = new PublicApplicationDTO(
                    p.getUserID(),
                    p.getName(),
                    p.getSurname(),
                    p.getSsn(),
                    p.getEmail(),
                    hired,
                    p.getRegistrationdate(),
                    experienceDTOList,
                    availabilities
            );
            applicationList.add(tempPA);
        }
        LOG.log(Level.INFO, "All applicants fetched.");
        return applicationList;

        }catch (Exception e) {
            LOG.info("Exception in integration layer: " + e);
        }
        return null;
    }
}
