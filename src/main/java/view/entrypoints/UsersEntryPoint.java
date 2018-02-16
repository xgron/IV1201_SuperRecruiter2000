package view.entrypoints;


import controller.HomeController;
import model.ErrorHandling;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import shared.*;
import view.ConversionService;
import view.response.AppRest;
import view.response.UserRest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfet on 2018-01-29.
 */



@Path("/users")
public class UsersEntryPoint {


    /**
     *   This method is to be called when desiring to create a user
     *  In case the username is available a corresponding object containing
     *  the new user's userID will be returned
     * <p>
     * In case the username is not availabe an appropriate error message will
     * be sent
     *
     * @param  username  the requested username
     * @param  password the desired password for the user
     * @param  email the email of the user
     * @param surname the surname of the user
     * @param firstname the first name of the user
     * @param ssn the user's social security number
     * @return a Json object with the corresponding userID or an error message
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDTO createUser(UserRest requestObject) {
        PersonDTO personDTO = new PersonDTO();
        System.out.println(requestObject);
        BeanUtils.copyProperties(requestObject, personDTO);
        System.out.println(personDTO);
        HomeController userController = new HomeController();
        PersonDTO returnvalue = new PersonDTO();
        try{
            returnvalue = userController.registerUser(personDTO);
        }catch (ErrorHandling.RegisterUserException rue){
            //TO DO
            System.out.println("USER REGISTER EXCEPTION.");
        }


        return returnvalue ;
    }

    /**
     *  This method will give you all pertinent information regarding
     *  a user by giving it the id of the user
     * <p>
     * In case the username is not in the database an appropriate error message will
     * be sent
     *
     * @param  id the userId of the desired user
     * @return a Json object with the corresponding data related to the user
     */

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserRest getUserProfile(@PathParam("id") String id) {
        UserRest returnvalue =  new UserRest();

        returnvalue.setFirstName("Billy bob");

        return returnvalue;
    }

    /**
     * This method will give you the applicants from a desired
     * start to a limit
     * <p>
     * @param start whih user to start with
     * @param  limit which user to end on, if no value is given it will default to 1000
     * @return a Json object with the corresponding data related to the user
     */


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppRest> getApplicants(@DefaultValue("0") @QueryParam("start") int start,
                                                 @DefaultValue("1000")@QueryParam("limit") int limit) {
        HomeController hc = new HomeController();
        List<PublicApplicationDTO> paDTO = hc.getApplicants();
        ConversionService cconversion = new ConversionService();
        List<AppRest> returnValue = cconversion.convertApplication(paDTO);
        return returnValue;
    }


    /**
     * This method will take an application
     * of a registered user in the system
     * <p>
     * @param availability a list containing the dates the user is available
     * @param competencies a list of competencies the user possesses
     * @param  limit which user to end on, if no value is given it will default to 1000
     * @return a Json object with the corresponding data related to the user
     */

    @POST
    @Path("/{id}/application")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApplicationDTO getApplication (ApplicationDTO requestObject, @PathParam("id") String id)  {
       HomeController hc = new HomeController();
        requestObject.setUserID(id);
       try{

           hc.registerApplication(requestObject);
        }catch (ErrorHandling.RegisterApplicationException rae){
            //TO DO
            System.out.println("RU EXCEPTION");
        }


        System.out.println(requestObject);

        return requestObject;
    }

}
