package view.response;

import shared.DateDTO;
import shared.ExperienceDTO;

import javax.xml.bind.annotation.XmlRootElement;
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
}