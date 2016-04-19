/*
 *  Copyright James House (c) 2001-2004
 *
 *  All rights reserved. 
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 *
 *
 * This product uses and includes within its distribution, 
 * software developed by the Apache Software Foundation 
 *     (http://www.apache.org/)
 *
 */
package org.quartz.ui.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.terracotta.quartz.wrappers.TriggerWrapper;


/**
 * 
 * @since Feb 2, 2003
 * @version $Revision: 462 $
 * @author Erick Romson
 * @author Rene Eigenheer
 */

public class TriggerHelperl {
    private static transient final Log logger = LogFactory.getLog(TriggerHelperl.class);
    protected final static Object triggerLock = new Object();
    protected static HashMap triggersByGroup = new HashMap(25);
    /**
     * 
     * @param trigger
     * @return
     */
    public static String getTriggerType(Trigger trigger) {
	String type = null;
	if (trigger == null) {
	    return null;
	}

	if (trigger.getClass() == SimpleTrigger.class) {
	    type = "simple";
	} else if (trigger.getClass() == CronTrigger.class) {
	    type = "cron";
	} else {
	    type = trigger.getClass().getName();
	}
	return type;
    }

    /**
     * the method scheduler.getTriggersForJob not implemented
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @return
     * @throws ServletException
     */
    public static Trigger[] getTriggersFromJob(Scheduler scheduler, String jobName, String jobGroup)
	    throws ServletException, SchedulerException {
	List<Trigger> triggerList = new ArrayList<Trigger>();
	List<String> groups = null;
	try {
	    groups = scheduler.getTriggerGroupNames();
	 
	} catch (SchedulerException e) {
	    logger.error("When getting all trigger groups", e);
	    throw new ServletException("When getting all trigger groups", e);
	}

	for (String group : groups) {
	    Set<TriggerKey> names = null;
	    names = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
	    for (TriggerKey name : names) {
		Trigger trigger = null;
		try {
		    trigger = scheduler.getTrigger(name);
		} catch (SchedulerException e) {
		    logger.error("When getting trigger " + name + " in group " + group, e);
		    throw new ServletException("When getting trigger " + name + " in group " + group, e);
		}

		if (trigger == null) {
		    logger.warn("The trigger " + name + " in group " + group + " was null");
		    continue;
		}

		if (trigger.getJobKey().getName()
				.equals(jobName) && trigger.getJobKey().getGroup().equals(jobGroup)) {
		    triggerList.add(trigger);
		}
	    }
	}
	Trigger[] retArr = new Trigger[triggerList.size()];
	triggerList.toArray(retArr);
	return retArr;
    }
}

