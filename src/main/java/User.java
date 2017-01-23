import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.Id;

@Stateless
@Entity
public class User {


    public User() {
    }

    @Id
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;

    public User(String login, String password, String name, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
