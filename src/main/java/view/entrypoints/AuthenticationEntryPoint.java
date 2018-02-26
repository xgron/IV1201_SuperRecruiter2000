package view.entrypoints;


import controller.HomeController;
import model.ErrorHandling;
import shared.LoginDTO;
import shared.PersonDTO;
import view.response.AuthenticationDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDTO login(LoginDTO loginDetails){

        HomeController hc = new HomeController();

        /*
        try{
            returnvalue = hc.AuthenticateUser(loginDetails);
        }catch (ErrorHandling.AuthenticateUserException e){
            //TO DO
            System.out.println("USER REGISTER EXCEPTION.");
        }

        */
        return null;
    }

}
