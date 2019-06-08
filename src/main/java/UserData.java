
public class UserData {

    private String login;
    private String password;
    private boolean isRegistered;

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

    public boolean isRegistered() {
        return isRegistered;
    }
    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
    public String toString() {
        return "login: " + login + " password: " + password;
    }
}