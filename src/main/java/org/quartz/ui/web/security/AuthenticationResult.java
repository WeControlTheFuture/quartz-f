package org.quartz.ui.web.security;

/**
 * @author payne_m
 *
 * Class to hold result of authentication
 */
public class AuthenticationResult {

	
	boolean Authenticated = false;
	String details="Authentication not attempted";
	
    /**
     * 
     */
    public AuthenticationResult() {
        super();
    }
    /**
     * @param authenticated
     * @param details
     * @param principal
     */
    public AuthenticationResult(boolean authenticated, String details,
            Object principal) {
        super();
        Authenticated = authenticated;
        this.details = details;
        this.principal = principal;
    }
	Object principal; 
	
	/**
	 * @return Returns the authenticated.
	 */
	public boolean isAuthenticated() {
		return Authenticated;
	}
	/**
	 * @param authenticated The authenticated to set.
	 */
	public void setAuthenticated(boolean authenticated) {
		Authenticated = authenticated;
	}
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return Returns the principal.
	 */
	public Object getPrincipal() {
		return principal;
	}
	/**
	 * @param principal The principal to set.
	 */
	public void setPrincipal(Object principal) {
		this.principal = principal;
	}
}
