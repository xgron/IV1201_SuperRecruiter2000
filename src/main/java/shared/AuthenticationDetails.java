package shared;

public class AuthenticationDetails {
    private String userID;
    private String jwt;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getJWT() {
        return jwt;
    }

    public void setJWT(String jwt) {
        this.jwt = jwt;
    }
}
