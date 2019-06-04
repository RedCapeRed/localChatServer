
public class UserData {

    private String login;
    private char[] password;

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String pass="";
        for (char i:password)
            pass +=i;
        return "login: " + login + " password: " + pass;
    }
}