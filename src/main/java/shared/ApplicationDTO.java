package shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class ApplicationDTO {
    @XmlElement(name = "availabilities")
    private List<DateDTO> availabilities;
    @XmlElement(name = "experiences")
    private List<ExperienceDTO> experience;
    private String userID;

    public ApplicationDTO(){
        this.availabilities = new ArrayList<DateDTO>();
        this.experience = new ArrayList<ExperienceDTO>();
    }

    @Override
    public String
    toString() {
        return "ApplicationDTO{" +
                "availabilities=" + availabilities +
                ", experience=" + experience +
                ", userID='" + userID + '\'' +
                '}';
    }

    public ApplicationDTO(List<DateDTO> availabilities, List<ExperienceDTO> experience, String userID) {
        this.availabilities = availabilities;
        this.experience = experience;
        this.userID = userID;
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
