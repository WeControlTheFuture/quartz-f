/*
 * @since Jan 12, 2004
 * @version Revision: 
 * @author Matthew Payne
 *  
 * Basic Action for quartz actions
 */
package org.quartz.ui.web.action.base;

import org.quartz.ui.web.model.DefinitionManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Matthew Payne
 * 
 * Add job definition
 */
public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String JOB_DEFINITIONS_PROP = "definitionManager";

	public static DefinitionManager getDefinitionManager() {
		DefinitionManager manager = null;
		manager = (DefinitionManager) ActionContext.getContext().getApplication().get(JOB_DEFINITIONS_PROP);
		return manager;
	}

	public String logout() {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}

}
