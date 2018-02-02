package shared;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDTO {
    private List<DateDTO> availabilities;
    private List<ExperienceDTO> experience;
    private String userID;

    public ApplicationDTO(){
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
