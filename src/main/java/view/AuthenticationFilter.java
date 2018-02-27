package view;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.glassfish.jersey.server.ContainerRequest;
import shared.LoginDTO;
import shared.PersonDTO;
import view.response.AuthenticationDetails;

import javax.annotation.Priority;
import javax.servlet.ServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final String USER_CUT_OFF = "users/";
    private static final String SIGNING_KEY = "secretsigningkey";

    /**
     *  This method handles all logic related to verifying that the user is
     *  the one they´re claiming to be through checking whether or not they
     *  possess the right token related to the specific user.
     *
     * @param UserRest PersonDTO containing the relevant information related to the user
     *
     * * @return a Json object with the corresponding userID or an error message
     */

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        /*
        ContainerRequest cr = (ContainerRequest) requestContext;
        cr.bufferEntity();
        LoginDTO ad = cr.readEntity(LoginDTO.class);

        System.out.println(ad.getUsername() + " " + ad.getPassword());
*/
        String id = requestContext.getUriInfo().getPath().substring(USER_CUT_OFF.length());

        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        System.out.println(authorizationHeader);
        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        if(validateToken(token, id)) {
            System.out.println("Correct token");
        }
        else {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private boolean validateToken(String token, String user) {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        System.out.println("Userid is: " + user);
        System.out.println("The token is " + token);
       return user.equals(Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getSubject());

    }
}
