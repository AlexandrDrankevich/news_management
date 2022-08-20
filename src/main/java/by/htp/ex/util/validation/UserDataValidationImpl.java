package by.htp.ex.util.validation;

import by.htp.ex.bean.NewUserInfo;

import java.util.regex.Pattern;

public class UserDataValidationImpl implements UserDataValidation {
    @Override
    public boolean checkAuthData(String login, String password) {
        if (isEmptyData(password, login)) {
            return false;
        }
        if (Pattern.matches("[A-Z a-z 0-9]+", password) && Pattern.matches("[a-z 0-9]+@[a-z]+.[a-z]{2,3}", login)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRegData(NewUserInfo user) {
        String password = user.getPassword();
        String login = user.getLogin();
        String name = user.getName();
        String surname = user.getSurname();
        String birthday = user.getBirthday();
        if (isEmptyData(password, login, name, surname, birthday)) {
            return false;
        }
        if (Pattern.matches("[a-z 0-9]+@[a-z]+.[a-z]{2,3}", login)
                && Pattern.matches("[A-Z a-z]+", name)
                && Pattern.matches("[A-Z a-z]+", surname)) {
            return true;
        }
        return false;
    }

    private boolean isEmptyData(String... data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
