package by.epamtc.restaurant.bean.user;

import java.io.Serializable;

public class UserAuthData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;

	public UserAuthData(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public UserAuthData() {
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
