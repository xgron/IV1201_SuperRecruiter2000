package shared;

import org.eclipse.persistence.sessions.Login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by enfet on 2018-02-12.
 */

@XmlRootElement
public class LoginDTO {
    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    public LoginDTO() {}

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
