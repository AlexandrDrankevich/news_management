package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserServiceImpl implements UserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
    private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();


    @Override
    public String signIn(String login, String password) throws ServiceException {
    	 if (!userDataValidation.checkAuthData(login,password)) {
    		 throw new ServiceException("invalid authoriztion data");
    	 }
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login, password);
            } else {
                return "guest";
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(NewUserInfo user) throws ServiceException {
        if (!userDataValidation.checkRegData(user)) {
            throw new ServiceException("invalid registration data");
        }
        try {
            return userDAO.registration(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
