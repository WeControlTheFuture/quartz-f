package org.quartz.ui.web.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.quartz.ui.web.security.AuthenticationResult;
import org.quartz.ui.web.security.Authenticator;
import org.quartz.ui.web.security.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogonAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    /**
     * 
     */
    private static final long serialVersionUID = -8700930409409064484L;

    private static final Log log = LogFactory.getLog(LogonAction.class);

    protected static final String USERNAME_COOKIE = "username";
    protected static final String PASSWORD_COOKIE = "password";

    String username;
    String password;

    boolean storeCookie;
    String executable;

    private HttpServletRequest request;
    private HttpServletResponse response;

    Authenticator authenticator;

    /**
     * @param authenticator
     *                The authenticator to set.
     */
    public void setAuthenticator(Authenticator authenticator) {
	this.authenticator = authenticator;
    }

    public String logon() throws Exception {

	User admin = new User();
	admin.setUsername(username);
	admin.setPassword(password);

	AuthenticationResult result = authenticator.authenticate(admin);

	if (!result.isAuthenticated()) {

//	    request.getSession().invalidate();
	    addActionError(getText("admin.authfailed"));
	    return INPUT;
	} else {

	    Map session = ActionContext.getContext().getSession();
	    // Setup the user session.
	    admin = (User) result.getPrincipal();
	    session.put(User.SESSION_NAME, admin);

	    log.info("User " + username + " logged in");

	    if (storeCookie) {
		Cookie cookie = new Cookie(USERNAME_COOKIE, username);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(864000); // 10 days
		response.addCookie(cookie);
	    }

	}

	return SUCCESS;
    }

    public String logout() throws Exception {
	ActionContext.getContext().getSession().clear();
	// this.deleteCookies(request, response, PASSWORD_COOKIE);
	// this.deleteCookies(request, response, USERNAME_COOKIE);
	return SUCCESS;
    }

    protected void deleteCookies(HttpServletRequest request, HttpServletResponse response, String name) {
	Cookie cookie = getCookies(request, name);
	if (cookie != null) {
	    cookie.setMaxAge(0);
	    cookie.setPath("/");
	    response.addCookie(cookie);
	}
    }

    /**
     * Convenience method to get {@link Cookie}s by name.
     * 
     * @param request
     *                The HTTP request we are processing
     * @param response
     *                The HTTP response we are processing
     * @param name
     *                The {@link Cookie} name
     */
    protected Cookie getCookies(HttpServletRequest request, String name) {
	Cookie[] cookies = request.getCookies();
	// return a empty cookie
	Cookie cookie = new Cookie("", "");

	if (cookies != null) {
	    for (Cookie thisCookie : cookies) {
		if (thisCookie.getName().equals(name)) {
		    if (!thisCookie.getValue().equals("")) {
			cookie = thisCookie;

			break;
		    }
		}
	    }
	}

	return cookie;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *                The password to set.
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
     *                The username to set.
     */
    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public String execute() {
	// TODO get user cookie

	/*
	 * if (cookies != null && cookies.length > 0) { cat.debug("Found " +
	 * cookies.length + " cookies"); for (int i=0; i<cookies.length; i++) {
	 * String name = cookies[i].getName(); cat.debug("Cookie " + i + "
	 * name=" + name + " value=" + cookies[i].getValue()); if
	 * (name.equals("username")) { theForm.setValue("username",
	 * cookies[i].getValue()); //} else if (name.equals("password")) { //
	 * theForm.setValue("password", cookies[i].getValue()); } }
	 */
	return SUCCESS;// just go to logon form
    }

    /**
     * @return Returns the storeCookie.
     */
    public boolean isStoreCookie() {
	return storeCookie;
    }

    /**
     * @param storeCookie
     *                The storeCookie to set.
     */
    public void setStoreCookie(boolean storeCookie) {
	this.storeCookie = storeCookie;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.webwork.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
     */
    public void setServletRequest(HttpServletRequest request) {
	this.request = request;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.webwork.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
     */
    public void setServletResponse(HttpServletResponse response) {
	this.response = response;
    }

}
