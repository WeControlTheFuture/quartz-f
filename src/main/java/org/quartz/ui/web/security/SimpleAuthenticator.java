package org.quartz.ui.web.security;

/**
 * Simple Xml implementation of the SecurityRealmInterface.
 * 
 * There is one user: username is 'username', password is 'password' And this
 * user is in one role: 'inthisrole'
 * 
 * @author Matthew Payne (matthew.payne@sutternow.com)
 */
public class SimpleAuthenticator implements Authenticator {

	Users users;

	public SimpleAuthenticator() {

	}

	private String username = "";
	private String password = "";

	/**
	 * @return Returns the users.
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            The users to set.
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Authenticate a user.
	 * 
	 * Implement this method in a subclass to avoid dealing with Principal
	 * objects.
	 * 
	 * @param username
	 *            a username
	 * @param password
	 *            a plain text password, as entered by the user
	 * 
	 * @return null if the user cannot be authenticated, otherwise a Pricipal
	 *         object is returned
	 */

	public AuthenticationResult authenticate(UserDetails userDetails) throws Exception {
		AuthenticationResult result = null;

		if (username.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
			result = new AuthenticationResult(true, SUCCESS, new User(username, password));
			;
		} else {

			result = new AuthenticationResult(false, FAIL, null);

		}

		return result;
	}
}

// ----------------------------------------------------------------------------
// EOF
