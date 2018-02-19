package view.response;

import shared.DateDTO;
import shared.ExperienceDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfet on 2018-02-02.
 */

@XmlRootElement
public class AppRest {
    private List<DateDTO> availabilities;
    private List<ExperienceDTO> experience;
    private String userID;
    private String name;
    private String surname;
    private int ssn;
    private String email;
    private String hired;
    private java.sql.Date registrationdate;


    public AppRest(){
        this.availabilities = new ArrayList<DateDTO>();
        this.experience = new ArrayList<ExperienceDTO>();
    }

    public List<DateDTO> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<DateDTO> availabilities) {
        this.availabilities = availabilities;
    }

    public List<ExperienceDTO> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceDTO> experience) {
        this.experience = experience;
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
}