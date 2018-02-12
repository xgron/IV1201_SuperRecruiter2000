package shared;

import integration.entity.Availability;
import integration.entity.Experience;

import java.sql.Date;
import java.util.List;

public class PublicApplicationDTO {
    private String userID;
    private String name;
    private String surname;
    private int ssn;
    private String email;
    private boolean hired;
    private java.sql.Date registrationdate;
    private List<Experience> experiences;
    private List<Availability> availabilities;

    public PublicApplicationDTO() {
    }

    public PublicApplicationDTO(String userID, String name, String surname, int ssn, String email, boolean hired, Date registrationdate, List<Experience> experiences, List<Availability> availabilities) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.hired = hired;
        this.registrationdate = registrationdate;
        this.experiences = experiences;
        this.availabilities = availabilities;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public String toString() {
        return "PublicApplicationDTO{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ssn=" + ssn +
                ", email='" + email + '\'' +
                ", hired=" + hired +
                ", registrationdate=" + registrationdate +
                ", experiences=" + experiences +
                ", availabilities=" + availabilities +
                '}';
    }
}
