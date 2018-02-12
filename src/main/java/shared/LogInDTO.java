package shared;

/**
 * Created by enfet on 2018-02-12.
 */
public class LogInDTO {
    private String username;
    private String password;

    public LogInDTO(String username, String password) {
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
