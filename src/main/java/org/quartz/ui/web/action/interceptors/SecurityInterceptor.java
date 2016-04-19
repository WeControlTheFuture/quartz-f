package org.quartz.ui.web.action.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * @author Matthew Payne
 * @since 12/19/2004
 * 
 * SecurityInterceptor - returns to logon if 
 *
 */
public class SecurityInterceptor implements Interceptor {
    //~ Methods ////////////////////////////////////////////////////////////////

    /**
     * 
     */
    private static final long serialVersionUID = -5534343211913643643L;
    private static final Log LOG = LogFactory.getLog(SecurityInterceptor.class);
    private static final String DEFAULT_FAIL_RESULT = "logon" ;
    private static final String DEFAULT_USER_TOKEN = "signedIn" ;
    
    String failResult = DEFAULT_FAIL_RESULT;
    String userToken = DEFAULT_USER_TOKEN;
    
    
    public String getFailResult() {
        return failResult;
    }
    public void setFailResult(String failResult) {
        this.failResult = failResult;
    }
   
    public String getUserToken() {
        return userToken;
   
    }
    
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public void destroy() {
    }

    public void init() {
    }
 
    public String intercept(ActionInvocation invocation) throws Exception {

        String actionName = invocation.getInvocationContext().getName();

        Object userObject = ActionContext.getContext().getSession().get(userToken);
	    if (userObject == null && !isSetupRequired()) {
	     	if (LOG.isDebugEnabled()) {  	
        		LOG.debug("User appeared not to logged in for action:" + actionName);
        	}
	    	return failResult;
        } 
	 	return invocation.invoke();
    }
    
    private boolean isSetupRequired() {
    	
    	Object obj = ActionContext.getContext().getSession().get("SETUP_REQUIRED");
    	if (obj != null) {
    	 	return true;
    	} 
    	return false;
    }
    
}
