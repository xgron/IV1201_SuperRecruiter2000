package view.entrypoints;


import shared.LoginDTO;
import view.response.AuthenticationDetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationDetails userLogin(LoginDTO loginDetails) {
        AuthenticationDetails returnvalue = new AuthenticationDetails();
        return returnvalue;
    }

}
