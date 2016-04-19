/*
 * Created on Jan 15, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.quartz.ui.web.action.definitions;
 
import java.util.Collection;
import java.util.Map;

import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.model.JobDefinition;

/**
 * @author sergei
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Load  extends BaseAction {

	 
   /**
     * 
     */
    private static final long serialVersionUID = -8789546841183859383L;
private String definitionName;
   JobDefinition _definition = new JobDefinition();
	 
   @Override
public String execute()  {

		   if (definitionName == null  || definitionName.length() < 1) {
				// this is fine.  No definition loaded(new)
				return INPUT;
		   } else {
			
			   _definition = BaseAction.getDefinitionManager().getDefinition(definitionName);
			
			   return SUCCESS;	 
		   }
		
		
	   }

   
   public Map getDefinitions() {
	   return getDefinitionManager().getDefinitions();
   }
   
   
   public String list()  {
   		return SUCCESS;	
   }
   


	/**
	 * @return
	 */
	public JobDefinition getDefinition() {
		return _definition;
	}


	/**
	 * @return
	 */
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}


}
