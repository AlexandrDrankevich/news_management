package by.htp.ex.bean;

public class NewUserInfo {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String birthday;

    public NewUserInfo() {
    }

    public NewUserInfo(String name, String surname, String login, String password, String birthday) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", surname=" + surname + ", login=" + login + ", password=" + password
                + ", birthday=" + birthday + "]";
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
}

