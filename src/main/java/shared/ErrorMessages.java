package shared;

public enum ErrorMessages {
    INVALID_USERID_MESSAGE("Invalid UserID!"),
    INVALID_COMPETENCE_MESSAGE("This competence does not exist!"),
    INVALID_AVAILABILITY_MESSAGE("Invalid availabilities!"),
    INVALID_USERNAME_MESSAGE("Invalid username!"),
    INVALID_PASSWORD_MESSAGE("Invalid password!"),
    EXISTING_SSN_MESSAGE("SSN already exists!"),
    EXISTING_USERNAME_MESSAGE("Username already exists!"),
    AUTHORIZATION_MESSAGE("Unauthorized Request!");




    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}