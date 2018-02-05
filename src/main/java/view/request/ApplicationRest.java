package view.request;

import javax.ws.rs.core.Application;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enfet on 2018-02-05.
 */


@XmlRootElement
public class ApplicationRest {
    @XmlElement(name = "availabilities")
    private List<DateRest> availabilities;
    @XmlElement(name = "experiences")
    private List<ExperienceRest> experience;
    private String userID;

    public ApplicationRest(){
        this.availabilities = new ArrayList<DateRest>();
        this.experience = new ArrayList<ExperienceRest>();
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

    public List<DateRest> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<DateRest> availabilities) {
        this.availabilities = availabilities;
    }

    public List<ExperienceRest> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceRest> experience) {
        this.experience = experience;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
