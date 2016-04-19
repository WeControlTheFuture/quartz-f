package org.quartz.ui.web.security;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Payne
 *
 */
public class User implements UserDetails{
	
      public static final String SESSION_NAME = User.class.getSimpleName() + ".SESSION_NAME";

    
	String username;
	String password;
	
	String email;
	String lastName;
	String firstName;

	Map roles;

	public User(String userName, String password) {
	    super();
	    this.username = userName;
	    this.password = password;
	}

	public User() {
		roles = new HashMap();
	}
	
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}
	/**
	 * @return Returns the roles.
	 */
	public Map getRoles() {
		return roles;
	}
	/**
	 * @param roles The roles to set.
	 */
	public void setRoles(Map roles) {
		this.roles = roles;
	}
}
