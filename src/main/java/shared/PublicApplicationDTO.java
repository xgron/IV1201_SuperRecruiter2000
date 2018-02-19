package shared;

import java.sql.Date;
import java.util.List;

public class PublicApplicationDTO {
    private String userID;
    private String name;
    private String surname;
    private int ssn;
    private String email;
    private String hired;
    private java.sql.Date registrationdate;
    private List<ExperienceDTO> experiences;
    private List<DateDTO> availabilities;

    public PublicApplicationDTO() {
    }

    public PublicApplicationDTO(String userID, String name, String surname, int ssn, String email, String hired, Date registrationdate, List<ExperienceDTO> experiences, List<DateDTO> availabilities) {
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

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<DateDTO> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<DateDTO> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public String toString() {
        return "PublicApplicationDTO{" +
                "\nuserID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ssn=" + ssn +
                ", email='" + email + '\'' +
                ", hired=" + hired +
                ", registrationdate=" + registrationdate +
                ", \n    experiences=" + experiences +
                ", \n    availabilities=" + availabilities +
                '}';
    }
}
