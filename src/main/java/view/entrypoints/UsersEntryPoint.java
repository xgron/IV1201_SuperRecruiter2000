package view.entrypoints;


import controller.HomeController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import model.ErrorHandling;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import shared.*;
import sun.rmi.runtime.Log;
import view.ConversionService;
import view.Secured;
import view.response.AppRest;
import view.response.UserRest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

/**
 * Created by enfet on 2018-01-29.
 */



@Path("/users")
public class    UsersEntryPoint {
    private static final String SIGNING_KEY = "secretsigningkey";
    private static final String AUTHENTICATION_SCHEME = "Bearer";



    /**
     *   This method is to be called when desiring to create a user
     *  In case the username is available a corresponding object containing
     *  the new user's userID will be returned
     * <p>
     * In case the username is not availabe an appropriate error message will
     * be sent
     *
     * @param UserRest PersonDTO containing the relevant information related to the user
     *
     * * @return a Json object with the corresponding userID or an error message
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
        }catch (ErrorHandling.CommonException rue){
            //TO DO
            System.out.println("USER REGISTER EXCEPTION.");
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }



        return returnvalue ;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDetails){
      HomeController hc = new HomeController();
        PersonDTO returnvalue = new PersonDTO();
        try{
            returnvalue = hc.AuthenticateUser(loginDetails);
        }catch (ErrorHandling.CommonException e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        String token = jwtBuilder(returnvalue.getUserId());
        returnvalue.setToken(token);
        return Response.ok().header(AUTHORIZATION, "Bearer " + token).entity(returnvalue).build();
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
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(@PathParam("id") String id) {
        System.out.println(id);
        HomeController hc = new HomeController();
        PublicApplicationDTO po = hc.getApplicant(id);
        po.toString();
        return Response.ok().entity(po).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeApplicationStatus(EvaluationDTO evaluationDTO, @PathParam("id") String id) {
        boolean evaluation = Boolean.parseBoolean(evaluationDTO.getEval());
        HomeController hc = new HomeController();
        try {
            Boolean result = hc.evaluateApplication(id, evaluation, evaluationDTO.getRecruiterID());
        }
        catch (ErrorHandling.CommonException e){
            return Response.serverError().entity(e.getMessage()).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }

        return Response.ok().build();
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
    public List<AppRest> getApplicants(@HeaderParam("AUTHORIZATION") String token,
                                       @DefaultValue("0") @QueryParam("start") int start,
                                                 @DefaultValue("1000")@QueryParam("limit") int limit) {
        HomeController hc = new HomeController();
        String recruiterID = extractUserId(token);
        List<PublicApplicationDTO> paDTO = hc.getApplicants(recruiterID);
        System.out.println("Hämtat applkationer");
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
       }catch (ErrorHandling.CommonException rae){
           System.out.println(rae.getMessage());
       }

        return requestObject;
    }

    private String extractUserId(String token) {
        token = token
                .substring(AUTHENTICATION_SCHEME.length()).trim();
        String userID = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getSubject();
        return userID;
    }


    //JAVADOC TO DO
    private String jwtBuilder(String userID) {

        String compactJws = Jwts.builder()
                .setSubject(userID)
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
        return compactJws;
    }

}



