package by.htp.ex.util.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import by.htp.ex.bean.NewUserInfo;

public class DataValidation {
	
	private final boolean result;
	private final Map<String,Boolean> validResults;
	

	
	private DataValidation(Builder builder) {
		this.validResults=builder.validResults;
		result=!validResults.containsValue(false);
		}
	
	public boolean isResult() {
		return result;
	}

	public Map<String, Boolean> getValidResults() {
		return validResults;
	}
	
	public static class Builder{
		private  Map<String,Boolean> validResults=new HashMap<>();
		  
		 public  Builder checkLogin(String login){
			 validResults.put("login", Pattern.matches("[a-z 0-9]+@[a-z]+.[a-z]{2,3}", login));
			 return (this);
		 }
			
			 public  Builder checkPassword(String password){
				 validResults.put("password", Pattern.matches("[A-Z a-z 0-9]+", password));
				 return (this); 
		 }
			 
			 public  Builder checkName(String name){
				 validResults.put("name", Pattern.matches("[A-Z a-z]+", name));
				 return (this); 
		 }
			 
			 public  Builder checkSurname(String surname){
				 validResults.put("surname", Pattern.matches("[A-Z a-z]+", surname));
				 return (this); 
		 }
			 
			 public Builder checkRegData(NewUserInfo user) {
				 String password = user.getPassword();
			        String login = user.getLogin();
			        String name = user.getName();
			        String surname = user.getSurname();
			        checkLogin(login).checkName(name).checkSurname(surname);
			        return(this);
			 }
			 
			 public DataValidation generateResult() {
				 return new DataValidation(this);
			 }
					 
	}

}
