package org.quartz.ui.web.security;

public interface Authenticator {

    
    public static String SUCCESS = "SUCCESS";
    public static String FAIL = "FAILURE";
   
	public abstract AuthenticationResult authenticate(UserDetails userDetails) throws Exception;
    
}
