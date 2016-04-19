/*
 * Created on May 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.quartz.ui.web.security;

import java.util.Map;

/**
 * @author Matthew Payne
 *
 */
public class Users {
	 private Map <String, User> userMap; 	
	
	 public User getUser(String name) {
	 	return userMap.get(name);
	 }
	 
	/**
	 * @return Returns the userMap.
	 */
	public Map <String, User> getUserMap() {
		return userMap;
	}
	/**
	 * @param userMap The userMap to set.
	 */
	public void setUserMap(Map <String, User> userMap) {
		this.userMap = userMap;
	}
}
