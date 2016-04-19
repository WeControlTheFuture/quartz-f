package org.quartz.ui.web.security;

import java.io.Serializable;

public interface UserDetails  extends Serializable  {


    /**
     * Returns the password used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the password (never <code>null</code>)
     */
    public String getPassword();

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    public String getUsername();


    
}
